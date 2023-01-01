import { useEffect, useState } from 'react'
const { kakao } = window

const Map = ({searchPlace})=>{

  useEffect(() => {
    var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 })
    const container = document.getElementById('myMap')
    const options = {
      center: new kakao.maps.LatLng(33.450701, 126.570667),
      level: 3,
    }
    const map = new kakao.maps.Map(container, options)

    const ps = new kakao.maps.services.Places()

    ps.keywordSearch(searchPlace, placesSearchCB)

    function placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        let bounds = new kakao.maps.LatLngBounds()

        // 최상위 검색결과만 마커 찍기
        displayMarker(data[0])
        bounds.extend(new kakao.maps.LatLng(data[0].y, data[0].x))
        
        // 전체 마커 다 찍기
        // for (let i = 0; i < data.length; i++) {
        //   displayMarker(data[i])
        //   bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x))
        // }

        map.setBounds(bounds)
      }
    }

    function displayMarker(place) {
      let marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x),
      })

      // 마커에 클릭이벤트를 등록합니다
      kakao.maps.event.addListener(marker, 'click', function () {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent('<div style="padding:5px;font-size:.8rem;">' + place.place_name + '</div>')
        infowindow.open(map, marker)
      })
    }
  }, [searchPlace])

  return (
      <div className='map'
        id="myMap"
        // style={{
        //   width: '500px',
        //   height: '500px',
        // }}
        >
     </div>
  )
}

export default Map