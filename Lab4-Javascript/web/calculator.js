$(document).ready(function(){
	var calc = {
		curOp : undefined, // stores the current operation function to use
		memoryValue : undefined, 
		lastNum : undefined // for storing last number when equals is repeatedly pressed
	};
	
	// Any number 0-9 or the '.' clicked
	$(".num").click(function(){
		var num = $(this).html();
		var scrn = document.getElementById("calcScreen");
		if(scrn.value == undefined || scrn.value == ""){
			if(num == '.')
				scrn.value = '0.';
			else
				setScreenVal(num);
		} else {
			scrn.value += num;
		}
		calc.lastNum = getScreenVal();
	});
	// Any operator clicked: + - * /
	var operatorClicked =  function(){
		calc.curOp = $(this).html();
		if(calc.memoryValue == undefined)
			calc.memoryValue = getScreenVal(); // store the old number in memory
		calc.lastNum = undefined;
		document.getElementById("calcScreen").value = "";
	};
	var equalsClicked = function(){
		var newNum = (calc.lastNum == undefined) ? getScreenVal() : calc.lastNum;
		calc.memoryValue = eval(calc.memoryValue + ' ' + calc.curOp + ' ' + newNum);
		setScreenVal(calc.memoryValue);
	};
	$(".operator").click(operatorClicked);
	$("#equals").click(equalsClicked);
	$("#clearScreen").click(function(){
		document.getElementById("calcScreen").value = "";
	});
	$("#showMem").click(function(){
		var scrn = document.getElementById("calcScreen");
		if(calc.memoryValue == undefined)
			scrn.value = "0";
		else
			setScreenVal(calc.memoryValue);
	});
	$("#clearMem").click(function(){
		calc.memoryValue = undefined;
		calc.lastNum = undefined;
	});
	$("#addMem").click(function(){
		document.getElementById("calcScreen").value = "";
		calc.lastNum = calc.memoryValue;
		equalsClicked();
	});
});

function getScreenVal(){
	return parseFloat(document.getElementById("calcScreen").value);
}
function setScreenVal(num){
	document.getElementById("calcScreen").value = Number(Number(num).toFixed(12)).toString();
}