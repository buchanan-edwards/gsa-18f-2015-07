package com.techflow.openfda.drug.client;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import java.io.IOException;
import java.net.URL;
import org.junit.Test;
import com.google.api.client.util.Charsets;
import com.google.common.io.Resources;
import com.techflow.openfda.GatewayException;
import com.techflow.openfda.drugs.DrugEventSummary;
import com.techflow.openfda.drugs.DrugLabel;
import com.techflow.openfda.drugs.Seriousness;

public class OpenFdaGatewayImplTest
{
	@Test
	public void shouldFindAspirin() throws GatewayException, IOException
	{
		final URL url = Resources.getResource("aspirin.json");
		final String text = Resources.toString(url, Charsets.UTF_8);
		final ContentProducingMockHttpTransport transport = new ContentProducingMockHttpTransport(text);
		final OpenFdaGatewayImpl g = new OpenFdaGatewayImpl();
		g.transport = transport;

		final DrugLabel drug = g.getLabel("aspirin");

		assertThat(
				transport.getMethod(),
				equalTo("GET"));
		assertThat(
				transport.getUrl(),
				equalTo("https://api.fda.gov/drug/label.json?search=brand_name:%22aspirin%22%20generic_name:%22aspirin%22"));
		assertThat(
				drug.getAskDoctor(),
				equalTo("Ask a doctor before use if stomach bleeding warning applies to you you have a history of stomach problems, such as heartburn you have high blood pressure, heart disease, liver cirrhosis, or kidney disease you are taking a diuretic you have asthma"));
		assertThat(
				drug.getAskDoctorOrPharmacist(),
				equalTo("Ask a doctor or pharmacist before use if you are taking a prescription drug for: anticoagulation (thinning of the blood) gout diabetes arthritis"));
		assertThat(
				drug.getDosage(),
				equalTo("Directions do not exceed recommended dosage drink a full glass of water with each dose adults and children 12 years of age and over: take 4 to 8 tablets every 4 hours not to exceed 48 tablets in 24 hours, unless directed by a doctor children under 12 years of age: consult a doctor"));
		assertThat(
				drug.getDoNotUse(),
				equalTo("Do not use if you have ever had an allergic reaction to any other pain reliever/fever reducer"));
		assertThat(
				drug.getStopUse(),
				equalTo("Stop use and ask a doctor if you experience any of the following signs of stomach bleeding: feel faint vomit blood have bloody or black stools have stomach pain that does not get better allergic reaction occurs ringing in the ears or a loss of hearing occurs pain gets worse or lasts more than 10 days fever gets worse or lasts more than 3 days any new symptoms appear redness or swelling is present in the painful area"));
		assertThat(
				drug.getActive(),
				equalTo("Active ingredient (in each tablet) Aspirin 81 mg (NSAID)* *nonsteroidal anti- inflammatory drug"));
		assertThat(
				drug.getInactive(),
				equalTo("Inactive ingredients *acetylated monoglycerides, *anhydrous lactose, *carnauba wax, colloidal silicon dioxide,*corn starch, *croscarmellose sodium, D&C Yellow #10 Aluminum Lake, FD&C Yellow #6 Aluminum Lake, hypromellose, *hypromellose phthalate, *iron oxide Yellow (iron oxide ochre), methacrylic acid copolymer, microcrystalline cellulose, *mineral oil, *polyethylene glycol (PEG)-400, *polysorbate 80, povidone, pregelatinized starch, *propylene glycol, *simethicone, silicon dioxide, sodium bicarbonate, sodium hydroxide, sodium lauryl sulfate, starch, stearic acid, talc, titanium dioxide, triacetin, and triethyl citrate. *May also contain."));
		assertThat(
				drug.getWarnings(),
				equalTo("Warnings Reye’s syndrome: Children and teenagers who have or are recovering from chicken pox or flu-like symptoms should not use this product. When using this product, if changes in behavior with nausea and vomiting occur, consult a doctor because these symptoms could be an early sign of Reye’s syndrome, a rare but serious illness. Allergy alert: Aspirin may cause a severe allergic reaction which may include: hives facial swelling asthma(wheezing) shock Stomach bleeding warning: This product contains a nonsteroidal anti-inflammatory drug (NSAID), which may cause stomach bleeding. The chance is higher if you: are age 60 or older have had stomach ulcers or bleeding problems take a blood thinning (anticoagulant) or steroid drug take other drugs containing prescription or nonprescription NSAIDs (aspirin, ibuprofen, naproxen, or others) have 3 or more alcoholic drinks every day while using this product take more or for a longer time than directed Do not use if you have ever had an allergic reaction to any other pain reliever/fever reducer Ask a doctor before use if stomach bleeding warning applies to you you have a history of stomach problems, such as heartburn you have high blood pressure, heart disease, liver cirrhosis, or kidney disease you are taking a diuretic you have asthma Ask a doctor or pharmacist before use if you are taking a prescription drug for: anticoagulation (thinning of the blood) gout diabetes arthritis Stop use and ask a doctor if you experience any of the following signs of stomach bleeding: feel faint vomit blood have bloody or black stools have stomach pain that does not get better allergic reaction occurs ringing in the ears or a loss of hearing occurs pain gets worse or lasts more than 10 days fever gets worse or lasts more than 3 days any new symptoms appear redness or swelling is present in the painful area If pregnant or breast- feeding, ask a health professional before use. It is especially important not to use aspirin during the last 3 months of pregnancy unless definitely directed to do so by a doctor because it may cause problems in the unborn child or complications during delivery. Keep out of reach of children In case of overdose, get medical help or contact a Poison Control Center right away."));
		assertThat(
				drug.getIndicationsAndUsage(),
				equalTo("Uses temporarily relieves minor aches and pains other therapy as recommended by your doctor. Because of its delayed action, this product will not provide fast relief of headaches, fever, or other symptoms needing immediate relief."));
		assertThat(
				drug.getGenericName(),
				equalTo("ASPIRIN"));
		assertThat(
				drug.getProductNdc(),
				equalTo("63941-440"));
	}

	@Test
	public void shouldFindSudafed() throws GatewayException, IOException
	{
		final URL url = Resources.getResource("sudafed.json");
		final String text = Resources.toString(url, Charsets.UTF_8);
		final ContentProducingMockHttpTransport transport = new ContentProducingMockHttpTransport(text);
		final OpenFdaGatewayImpl g = new OpenFdaGatewayImpl();
		g.transport = transport;

		final DrugLabel drug = g.getLabel("sudafed");

		assertThat(
				transport.getMethod(),
				equalTo("GET"));
		assertThat(
				transport.getUrl(),
				equalTo("https://api.fda.gov/drug/label.json?search=brand_name:%22sudafed%22%20generic_name:%22sudafed%22"));
		assertThat(
				drug.getIndicationsAndUsage(),
				equalTo("Use temporarily relieves nasal congestion due to the common cold, hay fever or other upper respiratory allergies"));
		assertThat(
				drug.getBrandName(),
				equalTo("Childrens Sudafed PE NASAL DECONGESTANT"));
		assertThat(
				drug.getGenericName(),
				equalTo("PHENYLEPHRINE HYDROCHLORIDE"));
		assertThat(
				drug.getPurpose(),
				equalTo("Purpose Nasal decongestant"));
		assertThat(
				drug.getAskDoctor(),
				equalTo("Ask a doctor before use if the child has heart disease high blood pressure thyroid disease diabetes a sodium-restricted diet"));
		assertThat(
				drug.getAskDoctorOrPharmacist(),
				nullValue());
		assertThat(
				drug.getDosage(),
				equalTo("Directions find right dose on chart below mL = milliliter; tsp = teaspoonful repeat dose every 4 hours do not use more than 6 times in 24 hours Age (yr) Dose (mL or tsp) under 4 years do not use 4 to 5 years 5 mL (1 tsp) 6 to 11 years 10 mL (2 tsp) Attention: use only enclosed dosing cup designed for use with this product. Do not use any other dosing device."));
		assertThat(
				drug.getDoNotUse(),
				equalTo("Do not use in a child who is taking a prescription monoamine oxidase inhibitor (MAOI) (certain drugs for depression, psychiatric or emotional conditions, or Parkinson's disease), or for 2 weeks after stopping the MAOI drug. If you do not know if your child's prescription drug contains an MAOI, ask a doctor or pharmacist before giving this product."));
		assertThat(
				drug.getStopUse(),
				equalTo("Stop use and ask a doctor if nervousness, dizziness, or sleeplessness occur symptoms do not improve within 7 days or occur with a fever"));
		assertThat(
				drug.getActive(),
				equalTo("Active ingredient (in each 5 mL = 1 teaspoonful) Phenylephrine HCl 2.5 mg"));
		assertThat(
				drug.getInactive(),
				equalTo("Inactive ingredients anhydrous citric acid, carboxymethylcellulose sodium, edetate disodium, FD&C red #40, flavors, glycerin, purified water, sodium benzoate, sodium citrate, sorbitol solution, sucralose"));
		assertThat(
				drug.getWarnings(),
				equalTo("Warnings Do not use in a child who is taking a prescription monoamine oxidase inhibitor (MAOI) (certain drugs for depression, psychiatric or emotional conditions, or Parkinson's disease), or for 2 weeks after stopping the MAOI drug. If you do not know if your child's prescription drug contains an MAOI, ask a doctor or pharmacist before giving this product. Ask a doctor before use if the child has heart disease high blood pressure thyroid disease diabetes a sodium-restricted diet When using this product do not exceed recommended dose Stop use and ask a doctor if nervousness, dizziness, or sleeplessness occur symptoms do not improve within 7 days or occur with a fever Keep out of reach of children. In case of overdose, get medical help or contact a Poison Control Center right away. (1-800-222-1222)"));
		assertThat(
				drug.getGenericName(),
				equalTo("PHENYLEPHRINE HYDROCHLORIDE"));
		assertThat(
				drug.getProductNdc(),
				equalTo("50580-784"));
	}

	@Test
	public void shouldFindAdverseReactions() throws GatewayException, IOException
	{
		final URL url = Resources.getResource("vyvanse.json");
		final String text = Resources.toString(url, Charsets.UTF_8);
		final ContentProducingMockHttpTransport transport = new ContentProducingMockHttpTransport(text);
		final OpenFdaGatewayImpl g = new OpenFdaGatewayImpl();
		g.transport = transport;

		final DrugLabel drug = g.getLabel("vyvanse");

		assertThat(
				drug.getAdverseReactions(),
				equalTo("6 ADVERSE REACTIONS The following adverse reactions are discussed in greater detail in other sections of the labeling Serious Cardiovascular Reactions [see Warnings and Precautions (5.2)] Blood Pressure and Heart Rate Increases [see Warnings and Precautions (5.3)] Psychiatric Adverse Reactions [see Warnings and Precautions (5.4)] Suppression of Growth [see Warnings and Precautions (5.5)] Peripheral Vasculopathy, including Raynaud's phenomenon [see Warnings and Precautions (5.6)] Most common adverse reactions (incidence ≥5% and at a rate at least twice placebo) in children, adolescents, and/or adults with ADHD were anorexia, anxiety, decreased appetite, decreased weight, diarrhea, dizziness, dry mouth, irritability, insomnia, nausea, upper abdominal pain, and vomiting (6.1) Most common adverse reactions (incidence ≥ 5% and at a rate at least twice placebo) in adults with BED were dry mouth, insomnia, decreased appetite, increased heart rate, constipation, feeling jittery, and anxiety (6.1) To report SUSPECTED ADVERSE REACTIONS, contact Shire US Inc. at 1-800-828-2088 or FDA at 1-800-FDA-1088 or www.fda.gov/medwatch 6.1 Clinical Trial Experience Because clinical trials are conducted under widely varying conditions, adverse reaction rates observed in the clinical trials of a drug cannot be directly compared to rates in the clinical trials of another drug and may not reflect the rates observed in practice. Attention Deficit Hyperactivity Disorder The safety data in this section is based on data from the 4-week parallel-group controlled clinical studies of VYVANSE in pediatric and adult patients with ADHD [see Clinical Studies (14.1)]. Adverse Reactions Associated with Discontinuation of Treatment in ADHD Clinical Trials In the controlled trial in patients ages 6 to 12 years (Study 1), 9% (20/218) of VYVANSE-treated patients discontinued due to adverse reactions compared to 1% (1/72) of placebo-treated patients. The most frequent adverse reactions leading to discontinuation (i.e. leading to discontinuation in at least 1% of VYVANSE-treated patients and at a rate at least twice that of placebo) were ECG voltage criteria for ventricular hypertrophy, tic, vomiting, psychomotor hyperactivity, insomnia, and rash [2 instances for each adverse reaction, i.e., 2/218 (1%)]. In the controlled trial in patients ages 13 to 17 years (Study 4), 4% (10/233) of VYVANSE-treated patients discontinued due to adverse reactions compared to 1% (1/77) of placebo-treated patients. The most frequent adverse reactions leading to discontinuation were irritability (3/233; 1%), decreased appetite (2/233; 1%), and insomnia (2/233; 1%). In the controlled adult trial (Study 7), 6% (21/358) of VYVANSE-treated patients discontinued due to adverse reactions compared to 2% (1/62) of placebo-treated patients. The most frequent adverse reactions leading to discontinuation (i.e. leading to discontinuation in at least 1% of VYVANSE-treated patients and at a rate at least twice that of placebo) were insomnia (8/358; 2%), tachycardia (3/358; 1%), irritability (2/358; 1%), hypertension (4/358; 1%), headache (2/358; 1%), anxiety (2/358; 1%), and dyspnea (3/358; 1%). The most common adverse reactions (incidence ≥5% and at a rate at least twice placebo) reported in children, adolescents, and/or adults were anorexia, anxiety, decreased appetite, decreased weight, diarrhea, dizziness, dry mouth, irritability, insomnia, nausea, upper abdominal pain, and vomiting. Adverse Reactions Occurring at an Incidence of 2% or More Among VYVANSE Treated Patients with ADHD in Clinical Trials Adverse reactions reported in the controlled trials in pediatric patients ages 6 to 12 years (Study 1), adolescent patients ages 13 to 17 years (Study 4), and adult patients (Study 7) treated with VYVANSE or placebo are presented in Tables 1, 2, and 3 below. Table 1 Adverse Reactions Reported by 2% or More of Children (Ages 6 to 12 Years) with ADHD Taking VYVANSE and at least Twice the Incidence in Patients Taking Placebo in a 4-Week Clinical Trial (Study 1) VYVANSE (n=218) Placebo (n=72) Decreased Appetite 39% 4% Insomnia 23% 3% Abdominal Pain Upper 12% 6% Irritability 10% 0% Vomiting 9% 4% Weight Decreased 9% 1% Nausea 6% 3% Dry Mouth 5% 0% Dizziness 5% 0% Affect lability 3% 0% Rash 3% 0% Pyrexia 2% 1% Somnolence 2% 1% Tic 2% 0% Table 2 Adverse Reactions Reported by 2% or More of Adolescent (Ages 13 to 17 Years) Patients with ADHD Taking VYVANSE and at least Twice the Incidence in Patients Taking Placebo in a 4-Week Clinical Trial (Study 4) VYVANSE (n=233) Placebo (n=77) Decreased Appetite 34% 3% Insomnia 13% 4% Weight Decreased 9% 0% Dry Mouth 4% 1% Table 3 Adverse Reactions Reported by 2% or More of Adult Patients with ADHD Taking VYVANSE and at least Twice the Incidence in Patients Taking Placebo in a 4-Week Clinical Trial (Study 7) VYVANSE (n=358) Placebo (n=62) Decreased Appetite 27% 2% Insomnia 27% 8% Dry Mouth 26% 3% Diarrhea 7% 0% Nausea 7% 0% Anxiety 6% 0% Anorexia 5% 0% Feeling Jittery 4% 0% Agitation 3% 0% Increased Blood Pressure 3% 0% Hyperhidrosis 3% 0% Restlessness 3% 0% Decreased Weight 3% 0% Dyspnea 2% 0% Increased Heart Rate 2% 0% Tremor 2% 0% In addition, in the adult population erectile dysfunction was observed in 2.6% of males on VYVANSE and 0% on placebo; decreased libido was observed in 1.4% of subjects on VYVANSE and 0% on placebo. Weight Loss and Slowing Growth Rate in Pediatric Patients with ADHD In a controlled trial of VYVANSE in children ages 6 to 12 years (Study 1), mean weight loss from baseline after 4 weeks of therapy was -0.9, -1.9, and -2.5 pounds, respectively, for patients receiving 30 mg, 50 mg, and 70 mg of VYVANSE, compared to a 1 pound weight gain for patients receiving placebo. Higher doses were associated with greater weight loss with 4 weeks of treatment. Careful follow-up for weight in children ages 6 to 12 years who received VYVANSE over 12 months suggests that consistently medicated children (i.e. treatment for 7 days per week throughout the year) have a slowing in growth rate, measured by body weight as demonstrated by an age- and sex-normalized mean change from baseline in percentile, of -13.4 over 1 year (average percentiles at baseline and 12 months were 60.9 and 47.2, respectively). In a 4-week controlled trial of VYVANSE in adolescents ages 13 to 17 years, mean weight loss from baseline to endpoint was -2.7, -4.3, and -4.8 lbs., respectively, for patients receiving 30 mg, 50 mg, and 70 mg of VYVANSE, compared to a 2.0 pound weight gain for patients receiving placebo. Careful follow-up of weight and height in children ages 7 to 10 years who were randomized to either methylphenidate or non-medication treatment groups over 14 months, as well as in naturalistic subgroups of newly methylphenidate-treated and non-medication treated children over 36 months (to the ages of 10 to 13 years), suggests that consistently medicated children (i.e. treatment for 7 days per week throughout the year) have a temporary slowing in growth rate (on average, a total of about 2 cm less growth in height and 2.7 kg less growth in weight over 3 years), without evidence of growth rebound during this period of development. In a controlled trial of amphetamine (d- to l-enantiomer ratio of 3:1) in adolescents, mean weight change from baseline within the initial 4 weeks of therapy was -1.1 pounds and -2.8 pounds, respectively, for patients receiving 10 mg and 20 mg of amphetamine. Higher doses were associated with greater weight loss within the initial 4 weeks of treatment [see Warnings and Precautions (5.5)]. Weight Loss in Adults with ADHD In the controlled adult trial (Study 7), mean weight loss after 4 weeks of therapy was 2.8 pounds, 3.1 pounds, and 4.3 pounds, for patients receiving final doses of 30 mg, 50 mg, and 70 mg of VYVANSE, respectively, compared to a mean weight gain of 0.5 pounds for patients receiving placebo. Binge Eating Disorder The safety data in this section is based on data from two 12 week parallel group, flexible-dose, placebo-controlled studies in adults with BED [see Clinical Studies 14.2]. Patients with cardiovascular risk factors other than obesity and smoking were excluded. Adverse Reactions Associated with Discontinuation of Treatment in BED Clinical Trials In controlled trials of patients ages 18 to 55 years, 5.1% (19/373) of VYVANSE-treated patients discontinued due to adverse reactions compared to 2.4% (9/372) of placebo-treated patients. No single adverse reaction led to discontinuation in 1% or more of VYVANSE-treated patients. The most common adverse reactions (incidence ≥5% and at a rate at least twice placebo) reported in adults were dry mouth, insomnia, decreased appetite, increased heart rate, constipation, feeling jittery, and anxiety. Adverse reactions reported in the pooled controlled trials in adult patients (Study 10 and 11) treated with VYVANSE or placebo are presented in Table 4 below. Table 4 Adverse Reactions Reported by 2% or More of Adult Patients with BED Taking VYVANSE and at least Twice the Incidence in Patients Taking Placebo in 12-Week Clinical Trials (Study 10 and 11) VYVANSE (N=373) Placebo (N=372) Dry Mouth 36% 7% InsomniaIncludes all preferred terms containing the word \"insomnia.\" 20% 8% Decreased Appetite 8% 2% Increased Heart RateIncludes the preferred terms heart rate increased and tachycardia. 7% 1% Feeling Jittery 6% 1% Constipation 6% 1% Anxiety 5% 1% Diarrhea 4% 2% Decreased Weight 4% 0% Hyperhidrosis 4% 0% Vomiting 2% 1% Gastroenteritis 2% 1% Paresthesia 2% 1% Pruritis 2% 1% Upper Abdominal Pain 2% 0% Energy Increased 2% 0% Urinary Tract Infection 2% 0% Nightmare 2% 0% Restlessness 2% 0% Oropharyngeal Pain 2% 0% 6.2 Postmarketing Experience The following adverse reactions have been identified during post approval use of VYVANSE. Because these reactions are reported voluntarily from a population of uncertain size, it is not possible to reliably estimate their frequency or establish a causal relationship to drug exposure. These events are as follows: palpitations, cardiomyopathy, mydriasis, diplopia, difficulties with visual accommodation, blurred vision, eosinophilic hepatitis, anaphylactic reaction, hypersensitivity, dyskinesia, tics, bruxism, depression, dermatillomania, aggression, Stevens-Johnson Syndrome, angioedema, urticaria, seizures, libido changes, frequent or prolonged erections, constipation, and rhabdomyolysis."));
		assertThat(
				drug.getDosage(),
				equalTo("2 DOSAGE AND ADMINISTRATION Indication Initial Dose Titration Schedule Recommended Dose Maximum Dose ADHD (2.2) 30mg every morning 10 mg or 20 mg weekly 30 mg to 70 mg per day 70 mg per day BED (2.3) 30mg every morning 20 mg weekly 50 mg to 70 mg per day 70 mg per day Prior to treatment, assess for presence of cardiac disease (2.4) Severe renal impairment: Maximum dose is 50 mg/day (2.5) End stage renal disease (ESRD): Maximum dose is 30 mg/day (2.5) 2.1 General Instructions for Use Take VYVANSE by mouth in the morning with or without food; avoid afternoon doses because of the potential for insomnia. VYVANSE may be administered in one of the following ways: Swallow VYVANSE capsules whole, or Open capsules, empty and mix the entire contents with yogurt, water, or orange juice. If the contents of the capsule include any compacted powder, a spoon may be used to break apart the powder. The contents should be mixed until completely dispersed. Consume the entire mixture immediately. It should not be stored. The active ingredient dissolves completely once dispersed; however, a film containing the inactive ingredients may remain in the glass or container once the mixture is consumed. Do not take anything less than one capsule per day, and a single capsule should not be divided. 2.2 Dosage for Treatment of ADHD The recommended starting dose is 30 mg once daily in the morning in patients ages 6 and above. Dosage may be adjusted in increments of 10 mg or 20 mg at approximately weekly intervals up to maximum dose of 70 mg/day. Patients may be maintained on their optimal dose [see Clinical Studies (14.1)]. 2.3 Dosage for Treatment of Moderate to Severe BED The recommended starting dose is 30 mg/day to be titrated in increments of 20 mg at approximately weekly intervals to achieve the recommended target dose of 50 to 70 mg/day. The maximum dose is 70 mg/day [see Clinical Studies (14.2)]. Discontinue VYVANSE if binge eating does not improve. 2.4 Important Information Prior to Dosing Prior to treating children, adolescents, and adults with CNS stimulants, assess for the presence of cardiac disease (e.g., a careful history, family history of sudden death or ventricular arrhythmia, and physical exam) [see Warnings and Precautions (5.2)]. To reduce the abuse of CNS stimulants including VYVANSE, assess the risk of abuse, prior to prescribing. After prescribing, keep careful prescription records, educate patients about abuse, monitor for signs of abuse and overdose, and re-evaluate the need for VYVANSE use [see Warnings and Precautions (5.1), Drug Abuse and Dependence (9.2, 9.3)]. 2.5 Dosage in Patients with Renal Impairment In patients with severe renal impairment (GFR 15 to < 30 mL/min/1.73 m2), the maximum dose should not exceed 50 mg/day. In patients with end stage renal disease (ESRD, GFR < 15 mL/min/1.73 m2), the maximum recommended dose is 30 mg/day [see Use in Specific Populations (8.6)]. 2.6 Dosage Modifications due to Drug Interactions Agents that alter urinary pH can impact urinary excretion and alter blood levels of amphetamine. Acidifying agents (e.g., ascorbic acid) decrease blood levels, while alkalinizing agents (e.g., sodium bicarbonate) increase blood levels. Adjust VYVANSE dosage accordingly [see Drug Interactions (7.1)]."));
		assertThat(
				drug.getManufacturerName(),
				equalTo("Shire LLC"));
	}

	@Test
	public void shouldStripOutTrailingCommas() throws GatewayException, IOException
	{
		final URL url = Resources.getResource("vyvanse.json");
		final String text = Resources.toString(url, Charsets.UTF_8);
		final ContentProducingMockHttpTransport transport = new ContentProducingMockHttpTransport(text);
		final OpenFdaGatewayImpl g = new OpenFdaGatewayImpl();
		g.transport = transport;

		g.getLabel("vyvanse,");

		assertThat(
				transport.getUrl(),
				equalTo("https://api.fda.gov/drug/label.json?search=brand_name:%22vyvanse%22%20generic_name:%22vyvanse%22"));
	}

	@Test
	public void shouldReplaceCommaWithSpace() throws GatewayException, IOException
	{
		final URL url = Resources.getResource("vyvanse.json");
		final String text = Resources.toString(url, Charsets.UTF_8);
		final ContentProducingMockHttpTransport transport = new ContentProducingMockHttpTransport(text);
		final OpenFdaGatewayImpl g = new OpenFdaGatewayImpl();
		g.transport = transport;

		g.getLabel("vyvanse,aspirin");

		assertThat(
				transport.getUrl(),
				equalTo("https://api.fda.gov/drug/label.json?search=brand_name:%22vyvanse%20aspirin%22%20generic_name:%22vyvanse%20aspirin%22"));
	}

	@Test
	public void shouldhandleEmptyResponse() throws GatewayException, IOException
	{
		final URL url = Resources.getResource("empty.json");
		final String text = Resources.toString(url, Charsets.UTF_8);
		final ContentProducingMockHttpTransport transport = new ContentProducingMockHttpTransport(text);
		final OpenFdaGatewayImpl g = new OpenFdaGatewayImpl();
		g.transport = transport;

		final DrugLabel drug = g.getLabel("empty");

		assertThat(
				drug.getAskDoctor(),
				nullValue());
		assertThat(
				drug.getAskDoctorOrPharmacist(),
				nullValue());
		assertThat(
				drug.getDosage(),
				nullValue());
		assertThat(
				drug.getDoNotUse(),
				nullValue());
		assertThat(
				drug.getStopUse(),
				nullValue());
		assertThat(
				drug.getActive(),
				nullValue());
		assertThat(
				drug.getInactive(),
				nullValue());
		assertThat(
				drug.getWarnings(),
				nullValue());
		assertThat(
				drug.getIndicationsAndUsage(),
				nullValue());
		assertThat(
				drug.getGenericName(),
				nullValue());
		assertThat(
				drug.getBrandName(),
				nullValue());
	}

	@Test
	public void shouldhandleMoreEmptyResponse() throws GatewayException, IOException
	{
		final URL url = Resources.getResource("missingopenfda.json");
		final String text = Resources.toString(url, Charsets.UTF_8);
		final ContentProducingMockHttpTransport transport = new ContentProducingMockHttpTransport(text);
		final OpenFdaGatewayImpl g = new OpenFdaGatewayImpl();
		g.transport = transport;

		final DrugLabel drug = g.getLabel("empty");

		assertThat(
				drug.getAskDoctor(),
				nullValue());
		assertThat(
				drug.getAskDoctorOrPharmacist(),
				nullValue());
		assertThat(
				drug.getDosage(),
				nullValue());
		assertThat(
				drug.getDoNotUse(),
				nullValue());
		assertThat(
				drug.getStopUse(),
				nullValue());
		assertThat(
				drug.getActive(),
				nullValue());
		assertThat(
				drug.getInactive(),
				nullValue());
		assertThat(
				drug.getWarnings(),
				nullValue());
		assertThat(
				drug.getIndicationsAndUsage(),
				nullValue());
		assertThat(
				drug.getGenericName(),
				nullValue());
	}

	@Test
	public void shouldhandleMissingResults() throws GatewayException, IOException
	{
		final URL url = Resources.getResource("missingresults.json");
		final String text = Resources.toString(url, Charsets.UTF_8);
		final ContentProducingMockHttpTransport transport = new ContentProducingMockHttpTransport(text);
		final OpenFdaGatewayImpl g = new OpenFdaGatewayImpl();
		g.transport = transport;

		final DrugLabel drug = g.getLabel("empty");

		assertThat(
				drug.getAskDoctor(),
				nullValue());
		assertThat(
				drug.getAskDoctorOrPharmacist(),
				nullValue());
		assertThat(
				drug.getDosage(),
				nullValue());
		assertThat(
				drug.getDoNotUse(),
				nullValue());
		assertThat(
				drug.getStopUse(),
				nullValue());
		assertThat(
				drug.getActive(),
				nullValue());
		assertThat(
				drug.getInactive(),
				nullValue());
		assertThat(
				drug.getWarnings(),
				nullValue());
		assertThat(
				drug.getIndicationsAndUsage(),
				nullValue());
		assertThat(
				drug.getGenericName(),
				nullValue());
	}

	@Test
	public void shouldReturnWarningsandPrecautions() throws GatewayException, IOException
	{
		final URL url = Resources.getResource("ambien.json");
		final String text = Resources.toString(url, Charsets.UTF_8);
		final ContentProducingMockHttpTransport transport = new ContentProducingMockHttpTransport(text);
		final OpenFdaGatewayImpl g = new OpenFdaGatewayImpl();
		g.transport = transport;

		final DrugLabel drug = g.getLabel("ambien");

		assertThat(
				drug.getWarningsAndCautions(),
				equalTo("5 WARNINGS AND PRECAUTIONS Need to evaluate for co-morbid diagnosis: Reevaluate if insomnia persists after 7 to 10 days of use (5.1) Severe anaphylactic/anaphylactoid reactions: Angioedema and anaphylaxis have been reported. Do not rechallenge if such reactions occur. (5.2) Abnormal thinking, behavioral changes and complex behaviors: May include \"sleep-driving\" and hallucinations. Immediately evaluate any new onset behavioral changes. (5.3) Depression: Worsening of depression or, suicidal thinking may occur. Prescribe the least amount feasible to avoid intentional overdose (5.3, 5.6) Withdrawal effects: Symptoms may occur with rapid dose reduction or discontinuation. (5.4, 9.3) CNS depressant effects: Use can impair alertness and motor coordination. If used in combination with other CNS depressants, dose reductions may be needed due to additive effects. Do not use with alcohol (2.3, 5.5) Elderly/debilitated patients: Use lower dose due to impaired motor, cognitive performance and increased sensitivity (2.2, 5.6) Patients with hepatic impairment, mild to moderate COPD, impaired drug metabolism or hemodynamic responses, mild to moderate sleep apnea: Use with caution and monitor closely. (5.6) 5.1 Need to evaluate for co-morbid diagnoses Because sleep disturbances may be the presenting manifestation of a physical and/or psychiatric disorder, symptomatic treatment of insomnia should be initiated only after a careful evaluation of the patient. The failure of insomnia to remit after 7 to 10 days of treatment may indicate the presence of a primary psychiatric and/or medical illness that should be evaluated. Worsening of insomnia or the emergence of new thinking or behavior abnormalities may be the consequence of an unrecognized psychiatric or physical disorder. Such findings have emerged during the course of treatment with sedative/hypnotic drugs, including zolpidem. 5.2 Severe anaphylactic and anaphylactoid reactions Rare cases of angioedema involving the tongue, glottis or larynx have been reported in patients after taking the first or subsequent doses of sedative-hypnotics, including zolpidem. Some patients have had additional symptoms such as dyspnea, throat closing or nausea and vomiting that suggest anaphylaxis. Some patients have required medical therapy in the emergency department. If angioedema involves the throat, glottis or larynx, airway obstruction may occur and be fatal. Patients who develop angioedema after treatment with zolpidem should not be rechallenged with the drug. 5.3 Abnormal thinking and behavioral changes A variety of abnormal thinking and behavior changes have been reported to occur in association with the use of sedative/hypnotics. Some of these changes may be characterized by decreased inhibition (e.g., aggressiveness and extroversion that seemed out of character), similar to effects produced by alcohol and other CNS depressants. Visual and auditory hallucinations have been reported as well as behavioral changes such as bizarre behavior, agitation and depersonalization. In controlled trials, < 1% of adults with insomnia who received zolpidem reported hallucinations. In a clinical trial, 7.4% of pediatric patients with insomnia associated with attention-deficit/hyperactivity disorder (ADHD), who received zolpidem reported hallucinations [see Use in Specific Populations (8.4)]. Complex behaviors such as \"sleep-driving\" (i.e., driving while not fully awake after ingestion of a sedative-hypnotic, with amnesia for the event) have been reported with sedative-hypnotics, including zolpidem. These events can occur in sedative-hypnotic-naive as well as in sedative-hypnotic-experienced persons. Although behaviors such as \"sleep-driving\" may occur with Ambien alone at therapeutic doses, the use of alcohol and other CNS depressants with Ambien appears to increase the risk of such behaviors, as does the use of Ambien at doses exceeding the maximum recommended dose. Due to the risk to the patient and the community, discontinuation of Ambien should be strongly considered for patients who report a \"sleep-driving\" episode. Other complex behaviors (e.g., preparing and eating food, making phone calls, or having sex) have been reported in patients who are not fully awake after taking a sedative-hypnotic. As with \"sleep-driving\", patients usually do not remember these events. Amnesia, anxiety and other neuro-psychiatric symptoms may occur unpredictably. In primarily depressed patients, worsening of depression, including suicidal thoughts and actions (including completed suicides), has been reported in association with the use of sedative/hypnotics. It can rarely be determined with certainty whether a particular instance of the abnormal behaviors listed above is drug induced, spontaneous in origin, or a result of an underlying psychiatric or physical disorder. Nonetheless, the emergence of any new behavioral sign or symptom of concern requires careful and immediate evaluation. 5.4 Withdrawal effects Following the rapid dose decrease or abrupt discontinuation of sedative/hypnotics, there have been reports of signs and symptoms similar to those associated with withdrawal from other CNS-depressant drugs [see Drug Abuse and Dependence (9)]. 5.5 CNS depressant effects Ambien, like other sedative/hypnotic drugs, has CNS-depressant effects. Due to the rapid onset of action, Ambien should only be taken immediately prior to going to bed. Patients should be cautioned against engaging in hazardous occupations requiring complete mental alertness or motor coordination such as operating machinery or driving a motor vehicle after ingesting the drug, including potential impairment of the performance of such activities that may occur the day following ingestion of Ambien. Ambien showed additive effects when combined with alcohol and should not be taken with alcohol. Patients should also be cautioned about possible combined effects with other CNS-depressant drugs. Dosage adjustments may be necessary when Ambien is administered with such agents because of the potentially additive effects. 5.6 Special populations Use in the elderly and/or debilitated patients: Impaired motor and/or cognitive performance after repeated exposure or unusual sensitivity to sedative/hypnotic drugs is a concern in the treatment of elderly and/or debilitated patients. Therefore, the recommended Ambien dosage is 5 mg in such patients to decrease the possibility of side effects [see Dosage and Administration (2.2)]. These patients should be closely monitored. Use in patients with concomitant illness: Clinical experience with Ambien (zolpidem tartrate) in patients with concomitant systemic illness is limited. Caution is advisable in using Ambien in patients with diseases or conditions that could affect metabolism or hemodynamic responses. Although studies did not reveal respiratory depressant effects at hypnotic doses of zolpidem in normal subjects or in patients with mild to moderate chronic obstructive pulmonary disease (COPD), a reduction in the Total Arousal Index together with a reduction in lowest oxygen saturation and increase in the times of oxygen desaturation below 80% and 90% was observed in patients with mild-to-moderate sleep apnea when treated with Ambien (10 mg) when compared to placebo. Since sedative/hypnotics have the capacity to depress respiratory drive, precautions should be taken if Ambien is prescribed to patients with compromised respiratory function. Post-marketing reports of respiratory insufficiency, most of which involved patients with pre-existing respiratory impairment, have been received. Ambien should be used with caution in patients with sleep apnea syndrome or myasthenia gravis. Data in end-stage renal failure patients repeatedly treated with Ambien did not demonstrate drug accumulation or alterations in pharmacokinetic parameters. No dosage adjustment in renally impaired patients is required; however, these patients should be closely monitored [see Clinical Pharmacology (12.3)]. A study in subjects with hepatic impairment did reveal prolonged elimination in this group; therefore, treatment should be initiated with 5 mg in patients with hepatic compromise, and they should be closely monitored [see Dosage and Administration (2.2) and Clinical Pharmacology (12.3)]. Use in patients with depression: As with other sedative/hypnotic drugs, Ambien should be administered with caution to patients exhibiting signs or symptoms of depression. Suicidal tendencies may be present in such patients and protective measures may be required. Intentional over-dosage is more common in this group of patients; therefore, the least amount of drug that is feasible should be prescribed for the patient at any one time. Use in pediatric patients: Safety and effectiveness of zolpidem have not been established in pediatric patients. In an 8-week study in pediatric patients (aged 6–17 years) with insomnia associated with ADHD, zolpidem did not decrease sleep latency compared to placebo. Hallucinations were reported in 7.4% of the pediatric patients who received zolpidem; none of the pediatric patients who received placebo reported hallucinations [see Use in Specific Populations (8.4)]."));
	}

	@Test
	public void getLabelShouldhandle404() throws GatewayException, IOException
	{
		final URL url = Resources.getResource("404.json");
		final String text = Resources.toString(url, Charsets.UTF_8);
		final ContentProducingMockHttpTransport transport = new ContentProducingMockHttpTransport(text, 404);
		final OpenFdaGatewayImpl g = new OpenFdaGatewayImpl();
		g.transport = transport;

		final DrugLabel drug = g.getLabel("empty");

		assertThat(drug, nullValue());
	}

	@Test
	public void getLabelShouldhandle500() throws GatewayException, IOException
	{
		final URL url = Resources.getResource("500.json");
		final String text = Resources.toString(url, Charsets.UTF_8);
		final ContentProducingMockHttpTransport transport = new ContentProducingMockHttpTransport(text, 500);
		final OpenFdaGatewayImpl g = new OpenFdaGatewayImpl();
		g.transport = transport;

		try {
			g.getLabel("empty");
		} catch (final GatewayException e) {
			return;
		}

		fail("Expected GatewayException");
	}

	@Test
	public void seriousDeath() throws IOException, GatewayException
	{
		final String text = readResource("effects/50242-051-death.json");
		final ContentProducingMockHttpTransport transport = new ContentProducingMockHttpTransport(text);
		final OpenFdaGatewayImpl g = new OpenFdaGatewayImpl();
		g.transport = transport;

		final DrugEventSummary effect = g.getEvents("50242-051", Seriousness.DEATH);

		assertThat(transport.getMethod(), equalTo("GET"));
		assertThat(transport.getUrl(), equalTo("https://api.fda.gov/drug/event.json?search=patient.drug.openfda.product_ndc:50242-051%20AND%20seriousnessdeath:1"));
		assertThat(effect.getCount(), equalTo(59744));
		assertThat(effect.getSeriousness(), equalTo("seriousnessdeath"));
	}

	@Test
	public void seriousnesscongenitalanomali() throws IOException, GatewayException
	{
		final String text = readResource("effects/65862-659-seriousnesscongenitalanomali.json");
		final ContentProducingMockHttpTransport transport = new ContentProducingMockHttpTransport(text);
		final OpenFdaGatewayImpl g = new OpenFdaGatewayImpl();
		g.transport = transport;

		final DrugEventSummary effect = g.getEvents("65862-659", Seriousness.CONGENITAL_ANOMALI);

		assertThat(transport.getMethod(), equalTo("GET"));
		assertThat(transport.getUrl(), equalTo("https://api.fda.gov/drug/event.json?search=patient.drug.openfda.product_ndc:65862-659%20AND%20seriousnesscongenitalanomali:1"));
		assertThat(effect.getCount(), equalTo(5675));
		assertThat(effect.getSeriousness(), equalTo("seriousnesscongenitalanomali"));
	}

	private String readResource(String resource) throws IOException
	{
		final URL url = Resources.getResource(resource);
		final String text = Resources.toString(url, Charsets.UTF_8);
		return text;
	}
}
