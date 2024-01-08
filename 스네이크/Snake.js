let size = 12;
let dataList = [];
let tdList = []; // 주소값
let dir = 1; // 0:북 1:동 2:남 3:서
let paly = null;
let item = -1;


// 게임 그리드 만들기
function init() {
  let $container = document.querySelector("#container");

  let $table = document.createElement("table");

  for (let i = 0; i < size; i++) {
    let $tr = document.createElement("tr");
    let dataTemp = [];
    let tdTemp = [];

    for (let j = 0; j < size; j++) {
      let $td = document.createElement("td");
      dataTemp.push(0);
      tdTemp.push($td);

      $tr.appendChild($td);
    }

    dataList.push(dataTemp); // 0 데이터 저장
    tdList.push(tdTemp);  // td 요소 저장 
    $table.appendChild($tr);
  }

  $container.appendChild($table);

  positionSnake();
  setItem();
}


// 뱀 그려넣기
function positionSnake() {
  for (let i = 0; i < snakeSize; i++) {
    dataList[curY[i]][curX[i]] = i + 1;

    tdList[curY[i]][curX[i]].setAttribute("class", "snakeBody");
    tdList[curY[snakeSize - 1]][curX[snakeSize - 1]].setAttribute("class", "snakeHead");
  }
  // console.log(dataList)


}

// 뱀 위치 표시하기
let snakeSize = 4;
let curY = [0, 0, 0, 0];
let curX = [0, 1, 2, 3];

// 뱀 이동
function moveSnake() {

  // 머리 좌표 복사
  let y = curY[snakeSize - 1];
  let x = curX[snakeSize - 1];

  // 머리 이동
  if (dir === 0) { // 0:북 1:동 2:남 3:서
    y--;
  } else if (dir === 1) {
    x++;
  } else if (dir === 2) {
    y++;
  } else if (dir === 3) {
    x--;
  }

  // 이동한 머리가 움직일 수 없는 부분에서 게임 오버
  if (x < 0 || x > dataList[0].length - 1 || y < 0 || y > dataList.length - 1) {
    gameOver();
    return;
  } else if (dataList[y][x] !== 0 && dataList[y][x] !== item) {
    gameOver();
    return;
  }

  // 위치 초기화
  for (let i = 0; i < snakeSize; i++) {
    dataList[curY[i]][curX[i]] = 0;
    tdList[curY[i]][curX[i]].removeAttribute("class");
  }

  // 게임오버가 아닐 때 움직임.

  let result = false;
  // 아이템을 먹었을 때
  if (dataList[y][x] === item) {
    curY.unshift(y);
    curX.unshift(x);
    snakeSize++;
    result = true;
  }

  // 스네이크 배열 변경
  for (let i = 1; i < snakeSize; i++) {
    curY[i - 1] = curY[i];
    curX[i - 1] = curX[i];
  }
  curY[snakeSize - 1] = y;
  curX[snakeSize - 1] = x;

  // 스네이크 배열 적용
  positionSnake();

  if (result) {
    setItem();
  }

}

// 아이템 넣기
function setItem() {

  let result = false;
  while (true) {
    let r1 = parseInt(Math.random() * size);
    let r2 = parseInt(Math.random() * size);

    // console.log(`item-r1 : ${r1}`)
    // console.log(`item-r2 : ${r2}`)

    if (dataList[r1][r2] === 0) {
      dataList[r1][r2] = item;
      tdList[r1][r2].setAttribute("class", "item");

      result = true;
    }

    if(result){
      break;
    }
  }
}

function gameStart() {
  console.log("게임 시작!")

  let $gameStart = document.querySelector("#gameStart");
  $gameStart.children[0].removeAttribute("onclick");

  play = setInterval(moveSnake, 100);

}

function gameOver() {

  clearInterval(play);

  let $gameOver = document.querySelector("#gameOver");
  $gameOver.innerHTML = "Game Over! <button onclick='replay()'>재시작</button>"

}

function replay() {

  location.href = "demo02.html"
  // let $container = document.querySelector("#container");
  // if($container.innerHTML!==""){
  //   $container.innerHTML = "";
  //   init();
  // }
}


// 방향 정하기
window.addEventListener("keydown", (e) => {
  let key = e.code;
  // 북(0) 동(1) 남(2) 서(3)

  if (key === "ArrowLeft") {
    dir = 3;
  } else if (key === "ArrowRight") {
    dir = 1;
  } else if (key === "ArrowDown") {
    dir = 2;
  } else if (key === "ArrowUp") {
    dir = 0;
  }

  // console.log(`dir : ${dir}`);
  // moveSnake();
})




init();

