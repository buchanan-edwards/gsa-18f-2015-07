<div id="${id}" class="col-sm-12">
    <div class="col-sm-4">
        <p><strong><g:message code="${code}" /></strong></p>
    </div>
    <div class="col-sm-11">
        <div class="controls">
            <g:if test="${valueList}">
                <g:if test="${valueList.size() > 1}">
                <ul>
                    <g:each in="${valueList}" var="itr">
                        <g:if test="${itr.class.toString() == 'class java.util.TreeMap$Entry'}">
                            <g:render template="viewListDetails" model='[id: "${id}", code: "${itr.collect { it.key }.flatten()[0].replaceAll("_", ".")}", valueList: itr.collect { it.value }.flatten() ]' />
                        </g:if>
                        <g:else>
                            <li>${itr}</li>
                        </g:else>

                    </g:each>
                </ul>
                </g:if>
                <g:else>
                    <p>${valueList?.first() ?: valueList}</p>
                </g:else>
            </g:if>
            <g:else>
                <g:message code="no.details" />
            </g:else>
        </div>
    </div>
</div>