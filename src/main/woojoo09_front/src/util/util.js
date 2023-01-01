const categories = [
  {
    name : "패션",
    value : "fashion"
  },
  {
    name : "뷰티",
    value : "beauty"
  },
  {
    name : "생활",
    value : "life"
  },
  {
    name : "식품",
    value : "food"
  },
  {
    name : "취미",
    value : "hobby"
  },
  {
    name : "반려동물",
    value : "pet"
  },
];

const getCategory = (category) =>{
  for(let e of categories){
    if(e.value === category) return e.name
  }
}

const citys = [
  {city: "Seoul", name : "서울시"},
  {city: "Seongnam", name : "성남시"},
  {city: "Suwon", name : "수원시"},
  {city: "Yongin", name : "용인시"},
  {city: "Hanam", name : "하남시"}
]

const towns = [
  {city: "서울시", town: "Gangnam", name : "강남구"},
  {city: "서울시", town: "Gangdong", name : "강동구"},
  {city: "서울시", town: "Gangbuk", name : "강북구"},
  {city: "서울시", town: "Gangseo", name : "강서구"},
  {city: "서울시", town: "Gwanak", name : "관악구"},
  {city: "서울시", town: "Gwangjin", name : "광진구"},
  {city: "서울시", town: "Guro", name : "구로구"},
  {city: "서울시", town: "Geumcheon", name : "금천구"},
  {city: "서울시", town: "Nowon", name : "노원구"},
  {city: "서울시", town: "Dobong", name : "도봉구"},
  {city: "서울시", town: "Dongdaemun", name : "동대문구"},
  {city: "서울시", town: "Dongjak", name : "동작구"},
  {city: "서울시", town: "Mapo", name : "마포구"},
  {city: "서울시", town: "Seodaemun", name : "서대문구"},
  {city: "서울시", town: "Seocho", name : "서초구"},
  {city: "서울시", town: "Sungdong", name : "성동구"},
  {city: "서울시", town: "Sungbuk", name : "성북구"},
  {city: "서울시", town: "Songpa", name : "송파구"},
  {city: "서울시", town: "Yangcheon", name : "양천구"},
  {city: "서울시", town: "Yeongdeungpo", name : "영등포구"},
  {city: "서울시", town: "Yongsan", name : "용산구"},
  {city: "서울시", town: "Eunpyeong", name : "은평구"},
  {city: "서울시", town: "Jongno", name : "종로구"},
  {city: "서울시", town: "Jung", name : "중구"},
  {city: "서울시", town: "Jungnang", name : "중랑구"},

  {city: "성남시", town: "Bundang", name : "분당구"},
  {city: "성남시", town: "Jung", name : "수정구"},
  {city: "성남시", town: "Jungnang", name : "중원구"},

  {city: "수원시", town: "Yeongtong", name : "영통구"},
  {city: "수원시", town: "Jangan", name : "장안구"},
  {city: "수원시", town: "Gwonseon", name : "권선구"},
  {city: "수원시", town: "Paldal", name : "팔달구"},

  {city: "용인시", town: "Giheung", name : "기흥구"},
  {city: "용인시", town: "Suji", name : "수지구"},
  {city: "용인시", town: "Cheoin", name : "처인구"},

]

const defaultImgs = {
  패션 : {
    imgUrl: "https://firebasestorage.googleapis.com/v0/b/developerkirbydrive.appspot.com/o/woojoo09%2Futil%2Ffashion_sample.png?alt=media&token=bccd92dc-1499-4ad9-bdb5-b33e469a6360"
  },
  뷰티 : {
    imgUrl : "https://firebasestorage.googleapis.com/v0/b/developerkirbydrive.appspot.com/o/woojoo09%2Futil%2Fbeauty_sample.png?alt=media&token=8f95a809-ba80-4fbe-bb7a-fd9f6eb69010"
  },
  생활 : {
    imgUrl : "https://firebasestorage.googleapis.com/v0/b/developerkirbydrive.appspot.com/o/woojoo09%2Futil%2Flife_sample.png?alt=media&token=cad35a69-1bb6-47aa-9d39-a09eaa74a77a"
  },
  식품 : {
    imgUrl : "https://firebasestorage.googleapis.com/v0/b/developerkirbydrive.appspot.com/o/woojoo09%2Futil%2Ffood_sample.png?alt=media&token=0299f8e3-732a-44b3-8024-257b07875063"
  },
  취미 : {
    imgUrl : "https://firebasestorage.googleapis.com/v0/b/developerkirbydrive.appspot.com/o/woojoo09%2Futil%2Fhobby_sample.png?alt=media&token=45de7960-2aa3-4fad-9809-2cd36dc0587e"
  },
  반려동물 : {
    imgUrl : "https://firebasestorage.googleapis.com/v0/b/developerkirbydrive.appspot.com/o/woojoo09%2Futil%2Fpet_sample.png?alt=media&token=a27d2dbd-66b4-44ec-a928-33ef99b1f0a8"
  },
}

const logisticsCompanys = [
  {logisticsCompany : "cj 대한통운", gisvalue:"cjlogistics"},
  {logisticsCompany : "우체국택배", gisvalue :"epost"},
  {logisticsCompany : "롯데택배", gisvalue :"lotteglogis"},
  {logisticsCompany : "한진택배", gisvalue :"hanjin"},
  {logisticsCompany : "로젠택배", gisvalue :"ilogen"},
  {logisticsCompany : "gs편의점택배", gisvalue :"gscvs"},
  {logisticsCompany : "cu편의점택배", gisvalue :"cupost"},
  {logisticsCompany : "gs반값택배", gisvalue :"gscvshalf"},
  {logisticsCompany : "cu끼리택배", gisvalue :"cupostbetweencu"},
  {logisticsCompany: "직접입력", gisvalue : "directInput"}
]

const BankKind = [
  {bankName: "KB국민", bankValue : "kbstar"}, 
  {bankName: "NH농협", bankValue : "nonghyup"}, 
  {bankName: "카카오뱅크", bankValue : "kakaobank"}, 
  {bankName: "신한", bankValue : "shinhan"}, 
  {bankName: "우리", bankValue : "woori"}, 
  {bankName: "IBK기업", bankValue : "ibk"}, 
  {bankName: "하나", bankValue : "kebhana"}, 
  {bankName: "직접입력", bankValue : "directInput"}

]

function uuidv4() {
  return ([1e7]+-1e3+-4e3+-8e3+-1e11).replace(/[018]/g, c =>
    (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
  );
}

export {categories, getCategory, citys, towns, defaultImgs, logisticsCompanys, BankKind, uuidv4}