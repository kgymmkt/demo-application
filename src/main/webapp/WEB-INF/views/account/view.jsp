<t:messagesPanel />
<form action="${contextPath}/accounts/${f:h(account.accountUuid)}" class="form-horizontal" method="get">
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-8">
            <a href="${contextPath}/accounts" class="btn btn-default pull-left"> <span
                class="glyphicon glyphicon-step-backward"></span> Account List
            </a>
            <button class="btn btn-default pull-right">
                <span class="glyphicon glyphicon-refresh"></span> Refresh
            </button>
        </div>
    </div>

    <jsp:include page="inc/accountDisplay.jsp" />

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-4">
            <button class="btn btn-default" name="form" value="edit">
                <span class="glyphicon glyphicon-edit"></span> Edit
            </button>
            <div class="btn btn-default" onclick="$('#deleteForm').submit();">
                <span class="glyphicon glyphicon-trash"></span> Delete
            </div>
        </div>
    </div>
</form>

<form:form id="deleteForm" action="${contextPath}/accounts/${f:h(account.accountUuid)}" class="sr-only" method="post">
    <input type="hidden" name="_method" value="delete">
</form:form>
