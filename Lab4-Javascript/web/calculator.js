$(document).ready(function(){
	var calc = {
		curOp : undefined, // stores the current operation function to use
		memoryValue : undefined, 
		lastNum : undefined, // for storing last number when equals is repeatedly pressed
		sequence : false
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
			if(scrn.value == calc.memoryValue){
				scrn.value = num;
			}
			else{
				scrn.value += num;
			}
		}
		calc.lastNum = getScreenVal();
	});
	// Any operator clicked: + - * /
	var operatorClicked =  function(){
		if(calc.memoryValue == undefined)
			calc.memoryValue = getScreenVal(); // store the old number in memory
		
		if(calc.sequence == false){
			document.getElementById("calcScreen").value = "";
		}else{
			calc.memoryValue = eval(calc.memoryValue + ' ' + calc.curOp + ' ' + calc.lastNum);
			document.getElementById("calcScreen").value = calc.memoryValue;
			
		}
		calc.curOp = $(this).html();
		calc.lastNum = undefined;
		calc.sequence = true;
	};
	var equalsClicked = function(){
		var newNum = (calc.lastNum == undefined) ? getScreenVal() : calc.lastNum;
		if(calc.memoryValue != undefined){
			calc.memoryValue = eval(calc.memoryValue + ' ' + calc.curOp + ' ' + newNum);
		}
		if(calc.memoryValue == undefined){
			calc.memoryValue = getScreenVal();
		}
		setScreenVal(calc.memoryValue);
		calc.sequence = false;
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
		calc.sequence = false;
	});
	$("#addMem").click(function(){
		var scrn = document.getElementById("calcScreen"); 
		calc.lastNum = scrn.value;
		calc.curOp = "+";
		equalsClicked();
	});
});

function getScreenVal(){
	return parseFloat(document.getElementById("calcScreen").value);
}
function setScreenVal(num){
	document.getElementById("calcScreen").value = Number(Number(num).toFixed(12)).toString();
}