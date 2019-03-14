google.charts.load('current', { packages: ['corechart'] });
google.charts.setOnLoadCallback(function() {
  drawChart(data);
});
function drawChart(data) {
  var chart_options = {
    width: 800,
    height: 400
  };

  var chart = new google.visualization.LineChart(
    document.getElementById('data')
  );

  chart.draw(data, chart_options);
}

function fetchMessageData() {
  fetch('/messagechart')
    .then(response => {
      return response.json();
    })
    .then(msgJson => {
      var msgData = new google.visualization.DataTable();
      //define columns for the DataTable instance
      msgData.addColumn('date', 'Date');
      msgData.addColumn('number', 'Message Count');

      for (i = 0; i < msgJson.length; i++) {
        msgRow = [];
        var timestampAsDate = new Date(msgJson[i].timestamp);
        var totalMessages = i + 1;

        msgRow.push(timestampAsDate, totalMessages);

        msgData.addRow(msgRow);
      }
      console.log(msgData);
      drawChart(msgData);
    });
}

fetchMessageData();
