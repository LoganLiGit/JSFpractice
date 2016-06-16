function initMap() {
  var latlng = new google.maps.LatLng(25,121.5);
  
  var locations = [
                   ['Bondi Beach', -33.890542, 151.274856, 4],
                   ['Coogee Beach', -33.923036, 151.259052, 5],
                   ['Cronulla Beach', -34.028249, 151.157507, 3],
                   ['Manly Beach', -33.80010128657071, 151.28747820854187, 2],
                   ['Maroubra Beach', -33.950198, 151.259302, 1]
                 ];
  
  var map = new google.maps.Map(document.getElementById('map'), {
    center: latlng,
    zoom: 7,
    styles: [{
      featureType: 'poi',
      stylers: [{ visibility: 'on' }]  // Turn off points of interest.
    }, {
      featureType: 'transit.station',
      stylers: [{ visibility: 'on' }]  // Turn off bus stations, train stations, etc.
    }],
    disableDoubleClickZoom: true
  });
  
  var infowindow = new google.maps.InfoWindow();
  
  var image='../images/burger.png';
 
  
  var marker, i;
  for (i = 0; i < locations.length; i++) {  
    marker = new google.maps.Marker({
      position: new google.maps.LatLng(locations[i][1], locations[i][2]),
      title: locations[i][0],
      zIndex: locations[i][3],
      map: map,
      icon:image
    });
    google.maps.event.addListener(marker, 'click', (function(marker, i) {
      return function() {
        infowindow.setContent(locations[i][0]);
        infowindow.open(map, marker);
      }
    })(marker, i));
  }
}