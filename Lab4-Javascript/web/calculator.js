function numClicked(num){
	calc.setCurNum((calc.curNum*10) + num);
}

function operatorClicked(op){
	if(op == '+') calc.curOp = calc.add;
	else if(op == '-') calc.curOp = calc.sub;
	else if(op == '*') calc.curOp = calc.mult;
	else if(op == '/') calc.curOp = calc.div;
	else console.log("ERROR: Operator not found: " + op);
	if(calc.memoryValue == undefined)
		calc.memoryValue = calc.curNum; // store the old number in memory
	calc.setCurNum(0);
}

function equalsClicked(){
	calc.doOperation();
}

function clearScreen(){
	calc.setCurNum(0);
}

function showMem(){
	var scrn = document.getElementById("screen");
	if(calc.memoryValue == undefined)
		scrn.value = "0";
	else
		scrn.value = calc.memoryValue;
}

function clearMem(){
	calc.memoryValue = 0;
}

function addMem(){
}

var calc = {
	curNum : 0,
	curOp : undefined,
	memoryValue : undefined,
	setCurNum : function(num){
		this.curNum = num;
		document.getElementById("screen").value = num;
	},
	add : function(){
		return this.memoryValue += this.curNum;
	},
	sub : function(){
		return this.memoryValue -= this.curNum;
	},
	mult : function(){
		return this.memoryValue *= this.curNum;
	},
	div : function(){
		return this.memoryValue /= this.curNum;
	},
	doOperation : function(){
		var scrn = document.getElementById("screen");
		var result = this.curOp();
		scrn.value = result;
	}
};