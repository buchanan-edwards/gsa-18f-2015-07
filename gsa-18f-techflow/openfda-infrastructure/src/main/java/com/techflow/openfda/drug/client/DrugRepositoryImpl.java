package com.techflow.openfda.drug.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hit;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.RAMDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DrugRepositoryImpl implements DrugRepository
{
	private static final Logger logger = LoggerFactory.getLogger(DrugRepositoryImpl.class.getName());

	private static int MAX_HITS = 5;

	private final RAMDirectory ramDir;

	public DrugRepositoryImpl()
	{
		IndexWriter indexWriter = null;
		final Analyzer analyzer = new StandardAnalyzer();
		ramDir = new RAMDirectory();

		try {
			indexWriter = new IndexWriter(ramDir, analyzer, true);
		} catch (final Exception e) {
			logger.info("FATAL: Could not create indexWriter.", e);
		}

		Scanner s = null;
		try {
			s = new Scanner(this.getClass().getResourceAsStream("/brand-names.txt"));
			while (s.hasNextLine()) {
				final Document document = new Document();
				final String drugName = s.nextLine();
				document.add(new Field("name", drugName, Field.Store.YES, Field.Index.UN_TOKENIZED));
				document.add(new Field("UCNAME", drugName.toUpperCase(), Field.Store.NO, Field.Index.TOKENIZED));
				indexWriter.addDocument(document);
			}
		} catch (final Exception e) {
			logger.info("ERROR: Exception scanning brand-names.txt", e);
		} finally {
			if (s != null) {
				s.close();
			}
		}

		try {
			indexWriter.optimize();
			logger.info("Index Buffer Size MB: " + indexWriter.getRAMBufferSizeMB());
			indexWriter.close();
		} catch (final Exception e) {
			logger.info("ERROR: Exception optimizing brand-names index.", e);
		}

	}

	@Override
	public List<String> startsWith(String drugName)
	{
		String searchName;
		final List<String> list = new ArrayList<String>();

		if (!drugName.endsWith("*")) {
			searchName = drugName.concat("*");
		} else {
			searchName = drugName;
		}

		IndexSearcher indexSearcher;
		try {

			indexSearcher = new IndexSearcher(ramDir);
			final Analyzer analyzer = new StandardAnalyzer();
			final QueryParser queryParser = new QueryParser("UCNAME", analyzer);
			final Query query = queryParser.parse(searchName);
			final Hits hits = indexSearcher.search(query);

			@SuppressWarnings("unchecked")
			final Iterator<Hit> it = hits.iterator();
			int count = 1;
			while (it.hasNext()) {
				final Hit hit = it.next();
				final Document document = hit.getDocument();
				final String name = document.get("name");
				list.add(name);
				if (++count > MAX_HITS) {
					break;
				}
			}

		} catch (final Exception e) {
			logger.info("ERROR: Exception searching brand-names.", e);
		}

		return list;
	}

}
