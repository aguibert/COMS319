function numClicked(num){
	var scrn = document.getElementById("calcScreen");
	if(scrn.value == undefined || scrn.value == ""){
		if(num == '.')
			setScreenVal(0.0);
		else
			setScreenVal(num);
	}
	else
		scrn.value += num;
	calc.lastNum = getScreenVal();
	console.log("val=" + getScreenVal());
}

function operatorClicked(op){
	if(op == '+') calc.curOp = calc.add;
	else if(op == '-') calc.curOp = calc.sub;
	else if(op == '*') calc.curOp = calc.mult;
	else if(op == '/') calc.curOp = calc.div;
	else console.log("ERROR: Operator not found: " + op);
	if(calc.memoryValue == undefined)
		calc.memoryValue = getScreenVal(); // store the old number in memory
	calc.lastNum = undefined;
	clearScreen();
}

function equalsClicked(){
	calc.doOperation();
}

function clearScreen(){
	document.getElementById("calcScreen").value = "";
}

function showMem(){
	var scrn = document.getElementById("calcScreen");
	if(calc.memoryValue == undefined)
		scrn.value = "0";
	else
		setScreenVal(calc.memoryValue);
}

function clearMem(){
	console.log("mem cleared");
	calc.memoryValue = undefined;
	calc.lastNum = undefined;
}

function addMem(){
	operatorClicked('+');
	calc.lastNum = calc.memoryValue;
	equalsClicked();
}

function getScreenVal(){
	return parseFloat(document.getElementById("calcScreen").value);
}
function setScreenVal(num){
	document.getElementById("calcScreen").value = Math.round(num*10)/10; // clears up any ugly floating point imprecision
}

var calc = {
	memoryValue : undefined,
	lastNum : undefined,
	add : function(toAdd){
		return this.memoryValue += toAdd;
	},
	sub : function(toSub){
		return this.memoryValue -= toSub;
	},
	mult : function(toMult){
		return this.memoryValue *= toMult;
	},
	div : function(toDiv){
		return this.memoryValue /= toDiv;
	},
	doOperation : function(){
		if(this.lastNum == undefined)
			setScreenVal(this.curOp(getScreenVal()));
		else
			setScreenVal(this.curOp(this.lastNum));
	}
};