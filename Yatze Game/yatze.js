let turnNumber = 0;
let rollNumber = 0;

function rollAll() {
  if (rollNumber < 3) {
    let dice = document.getElementsByClassName('die');
    for (let die of dice) {
      if (die.className !== 'die active') {
        let roll = (Math.floor((Math.random() * 6) + 1));
        updateDice(die.id, roll)
      }
    }
    let rollSpan = document.getElementById('rollSpan')
    rollNumber++
    rollSpan.textContent = rollNumber
  }
}
function updateDice(id, roll) {
  let dieDiv = document.getElementById(id);
  let content =  `<img style="width: 34px; z-index: 2;" id="${roll}" src="image/diefaces (${roll}).png"/>`
  dieDiv.innerHTML = content;
}
function lockDie(id) {
  let die = document.getElementById(id);
  if (die.innerHTML !== '') {
    if (die.className === 'die active') {
      die.style = 'border: 1px solid black;'
      die.className = 'die'
    } else {
      die.style = 'border: 2px solid rgb(255, 0, 0);'
      die.className += ' active'
    }
  }
}
function resetRollNumber() {
  rollNumber = 0;
  turnNumber++

  let rollSpan = document.getElementById('rollSpan')
  rollSpan.textContent = rollNumber
  let dice = document.getElementsByClassName('die');
  for (let die of dice) {
    die.innerHTML = ''
    die.style = 'border: 2px solid black;'
    die.className = 'die'
  }
  let upperSectionScore = document.getElementsByClassName('upper-scores')
  let totalTopSection = document.getElementById('upper-section')
  let upperArray = []
  for (let scores of upperSectionScore) {
    if (scores.textContent !== '') {
      upperArray.push(scores.textContent)
    }
  }
  if (upperArray.length === 6 && totalTopSection.textContent === '') {
    let upperSum = upperArray.reduce((a, b) => parseInt(a) + parseInt(b), 0);
    totalTopSection.textContent = upperSum
    if (upperSum >= 63) {
      totalTopSection.textContent += ' + 35 = ' + (upperSum + 35)
    }
  }
  if (turnNumber === 13) {
    endGame()
  }
}

function endGame() {
  let scoreSpans = document.getElementsByClassName('score-span');
  let totalScoresArray = []
  for (let span of scoreSpans) {
    if (span.textContent === '') {
      span.textContent = '0'
    }
    totalScoresArray.push(span.textContent)
  }
  let totalSum = totalScoresArray.reduce((a, b) => parseInt(a) + parseInt(b), 0);

  let topSection = document.getElementsByClassName('upper-scores')
  let topSectionArray = []
  for (let score of topSection) {
    topSectionArray.push(score.textContent)
  }
  let topSum = topSectionArray.reduce((a, b) => parseInt(a) + parseInt(b), 0);

  if (topSum >= 63) {
    totalSum = totalSum + 35
  }

  let rollBtn = document.getElementById('roll-btn')
  rollBtn.disabled = true
  let totalDiv = document.getElementById('total')
  totalDiv.textContent = totalSum
}

function sortDiceArray(number) {
  if (number === 'none') {
    let dice = document.getElementsByClassName('die');
    let diceArray = [];
    for (let die of dice) {
      let child = (die.firstElementChild || die.firstChild)
      diceArray.push(child.id)
    }
    let sortedArray = diceArray.sort((a, b) => parseInt(a) - parseInt(b))
    return sortedArray;
  } else {
    let dice = document.getElementsByClassName('die');
    let diceArray = [];
    for (let die of dice) {
      let child = (die.firstElementChild || die.firstChild)
      if (child.id == number) {
        diceArray.push(child.id)
      }
    }
    let sortedArray = diceArray.sort((a, b) => parseInt(a) - parseInt(b))
    return sortedArray;
  }
}

function scoreTop(divID, number) {
  let div = document.getElementById(divID);
  if (div.textContent === '' && rollNumber > 0) {
    let sortedDiceArray = sortDiceArray(number)
    let sum = sortedDiceArray.reduce((a, b) => parseInt(a) + parseInt(b), 0);
    div.textContent = sum
    resetRollNumber()
  }
}

function scoreYahtzee() {
  let yahtzee = document.getElementById('yahtzee')
  if (yahtzee.textContent === '' && rollNumber > 0) {
    let sortedDiceArray = sortDiceArray('none')
    let uniqueItems = [...new Set(sortedDiceArray)]
    if (uniqueItems.length === 1) {
      yahtzee.textContent = '50'
    } else {
      yahtzee.textContent = '0'
    }
    resetRollNumber()
  }
}

function reset() {
  let dice = document.getElementsByClassName('die');
  for (let die of dice) {
    die.textContent = ''
    die.style = 'border: 2px solid black;'
    die.className = 'die'
  }
  let scoreSpans = document.getElementsByClassName('score-span');
  for (let span of scoreSpans) {
    span.textContent = ''
  }
  rollNumber = 0
  turnNumber = 0
  let rollSpan = document.getElementById('rollSpan')
  rollSpan.textContent = rollNumber
  let totalDiv = document.getElementById('total')
  totalDiv.textContent = ''
  let upperDiv = document.getElementById('upper-section')
  upperDiv.textContent = ''
  let rollBtn = document.getElementById('roll-btn')
  rollBtn.disabled = false
}