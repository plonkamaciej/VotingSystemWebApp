function getTable(tableId) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
     document.getElementById(tableId).innerHTML = this.responseText;
    }
  };
  
  xhttp.open("GET", "voteSystem", true);
  xhttp.send();
}

function addCandidate(tableId, candidateName) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState === 4) {
      console.log('Response:', this.responseText); // Debugging statement
      if (this.status === 200) {
        // Update the table content after adding a candidate
        getTable(tableId);
      } else {
        console.error('Error:', this.status, this.statusText); // Debugging statement
      }
    }
  };

  // Open a POST request to the "voteSystem" endpoint
  xhttp.open("POST", "voteSystem", true);

  // Set the Content-Type header for form data
  xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

  // Send the request with the candidate name as a parameter
  xhttp.send("action=addCandidate&candidateName=" + encodeURIComponent(candidateName));
}
