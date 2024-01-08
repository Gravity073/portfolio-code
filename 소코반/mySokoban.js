let mouse = 1;
let mouseY = 1; // 쥐의 현재 Y 위치
let mouseX = 1; // 쥐의 현재 X 위치
let floor = 0;
let dir = 1;
let cheese = 2;
let goal = 3;
let goalCount = 3; // 목적지 개수
let tdList = [];
let desList = [];



let map = [
  [8, 8, 8, 8, 8, 8, 8, 8, 8, 8],
  [8, 0, 0, 0, 8, 8, 8, 3, 8, 8],
  [8, 0, 0, 0, 8, 8, 8, 3, 8, 8],
  [8, 0, 0, 0, 8, 8, 8, 3, 8, 8],
  [8, 8, 8, 0, 8, 8, 8, 0, 8, 8],
  [8, 8, 8, 0, 0, 0, 0, 0, 8, 8],
  [8, 8, 0, 0, 0, 8, 0, 0, 8, 8],
  [8, 8, 0, 0, 0, 8, 8, 8, 8, 8],
  [8, 8, 8, 8, 8, 8, 8, 8, 8, 8]
];


let coordinates = [
  [8, 8, 8, 8, 8, 8, 8, 8, 8, 8],
  [8, 1, 0, 0, 8, 8, 8, 0, 8, 8],
  [8, 0, 2, 2, 8, 8, 8, 0, 8, 8],
  [8, 0, 2, 0, 8, 8, 8, 0, 8, 8],
  [8, 8, 8, 0, 8, 8, 8, 0, 8, 8],
  [8, 8, 8, 0, 0, 0, 0, 0, 8, 8],
  [8, 8, 0, 0, 0, 8, 0, 0, 8, 8],
  [8, 8, 0, 0, 0, 8, 8, 8, 8, 8],
  [8, 8, 8, 8, 8, 8, 8, 8, 8, 8]
];

// 표 그리기  -- 게임 시작 1회성
function init() {

  let $center = document.querySelector("#center");

  let $table = document.createElement("table");
  for (let i = 0; i < map.length; i++) {
    let temp = [];
    let $tr = document.createElement("tr");
    for (let j = 0; j < map[i].length; j++) {
      let $td = document.createElement("td");
      temp.push($td);
      $tr.appendChild($td);
    }
    tdList.push(temp);
    $table.appendChild($tr);
  }

  $center.appendChild($table);
  console.log(tdList);
  console.log(map);

  pushMouse();
  draw();

}

// 색 넣기
function draw() {

  for (let i = 0; i < map.length; i++) {
    for (let j = 0; j < map[i].length; j++) {
      if (map[i][j] === 8) {
        tdList[i][j].setAttribute("class", "wall");
      } else if (map[i][j] === 0) {
        tdList[i][j].setAttribute("class", "zero");
      } else if (map[i][j] === 3) {
        tdList[i][j].setAttribute("class", "three");
      }

      if (coordinates[i][j] === 2) {
        tdList[i][j].setAttribute("class", "two");
      }

    }
  }
}

// dir에 따라 쥐 이미지 바꾸기
function pushMouse() {

  // 초기화
  if (tdList[mouseY][mouseX].innerHTML !== "") {
    tdList[mouseY][mouseX].innerHTML = "";
  }
  const img = new Image();

  if (dir === 1) {
    img.src = "./images/player_up.png"
  } else if (dir === 2) {
    img.src = "./images/player_right.png"
  } else if (dir === 3) {
    img.src = "./images/player_down.png"
  } else if (dir === 4) {
    img.src = "./images/player_left.png"
  }
  tdList[mouseY][mouseX].appendChild(img);

  draw();

}


// 쥐 이동하기
function moveMouse() {

  // 초기화
  if (tdList[mouseY][mouseX].innerHTML !== "") {
    tdList[mouseY][mouseX].innerHTML = "";
  }

  // 현재 위치 저장
  let y = mouseY;
  let x = mouseX;

  // 이동
  // 1:위 , 2:오른쪽, 3:아래, 4:왼쪽
  if (dir === 1) {
    mouseY--;
  } else if (dir === 2) {
    mouseX++;
  } else if (dir === 3) {
    mouseY++;
  } else if (dir === 4) {
    mouseX--;
  }

  if (coordinates[mouseY][mouseX] === 8) { // 움직인 곳이 벽일 때 좌표를 이전 위치로 수정
    mouseY = y;
    mouseX = x;
  } else if (coordinates[mouseY][mouseX] === 0) { // 움직인 곳이 땅(0)일 때 이전 좌표를 땅(0)으로 움직인 좌표를 1로 수정
    coordinates[y][x] = floor;
    coordinates[mouseY][mouseX] = mouse;
  } else if (coordinates[mouseY][mouseX] === 2) {  // 움직인 곳이 치즈(2)일 때, 그 다음 칸이 땅 or 목적지 (0)일 때
    // console.log(`dir : ${dir}`)

    // 치즈 좌표
    let y1 = mouseY;
    let x1 = mouseX;

    if (dir === 1) {
      y1--;
    } else if (dir === 2) {
      x1++;
    } else if (dir === 3) {
      y1++;
    } else if (dir === 4) {
      x1--;
    }
    // console.log(`y1 : ${y1}`)
    // console.log(`x1 : ${x1}`)

    if (coordinates[y1][x1] === 0) {
      coordinates[y][x] = floor;
      coordinates[mouseY][mouseX] = mouse;
      coordinates[y1][x1] = cheese;
      // console.log(coordinates)
    } else {
      console.log(`block`)
      mouseY = y;
      mouseX = x;
    }

  }

  win();

}

function win() {
  let count = 0;
  for (let i = 0; i < map.length; i++) {
    for (let j = 0; j < map[i].length; j++) {
      if (map[i][j] === 3 && coordinates[i][j] === 2) {
        count++;
      }
    }
  }

  if (count === goalCount) {

    setTimeout(() => {
      let $message = document.querySelector("#message");
      $message.innerHTML = "<span style= 'font-size:20px;'>Game Clear</span> <button onclick='replay()'>재시작</button>"
    }, 1000);

  }
}

function replay() {
  location.href = "mySokoban.html"
}




// 화면에서 쥐 방향 조작하는 이벤트
window.addEventListener("keydown", (e) => {
  let key = e.code;
  if (key === "ArrowUp") {
    dir = 1;
  } else if (key === "ArrowDown") {
    dir = 3;
  } else if (key === "ArrowLeft") {
    dir = 4;
  } else if (key === "ArrowRight") {
    dir = 2;
  } else if (key === "KeyA") {
    moveMouse();
  }

  pushMouse()
});



init();