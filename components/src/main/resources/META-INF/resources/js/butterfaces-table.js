function refreshTable(data, tableId) {
    var status = data.status;

    var $table = $(document.getElementById(tableId));

    console.log("Table id: " + tableId);
    console.log(status = data.status);
    // console.log($table);

    switch (status) {
        case "begin": // Before the ajax request is sent.
            // console.log('ajax request begin');
            $table.butterDisableElements();
            break;

        case "complete": // After the ajax response is arrived.
            // console.log('ajax request complete');
            break;

        case "success": // After update of HTML DOM based on ajax response..
            // console.log('ajax request success');
            $table.butterEnableElements();
            break;
    }
}