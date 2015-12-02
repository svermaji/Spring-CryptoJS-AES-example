	var DL = "__bcdef567kop48__";
	
    var secretKey =  new Date().getTime();
    
	function getArr6() {
		return [ 0, 1, 2, 3, 4, 5 ];
	}
	
	function shuffle(arr) {
		return arr.sort(function() {
			return .5 - Math.random();
		});
	};
	
	
	function getChar(n) {
		switch (n) {
		case 0:
			return 'a';
		case 1:
			return 'b';
		case 2:
			return 'c';
		case 3:
			return 'd';
		case 4:
			return 'e';
		case 5:
			return 'f';
		}
	};
	
	function change(idxtemp) {
		var nidx = shuffle(getArr6());
		for (i = 0; i < nidx.length / 2; i++) {
			idxtemp[nidx[i]] = getChar(idxtemp[nidx[i]]);
		}
		return idxtemp;
	};
	
	function getCipherText(keys){
		var fst = "";
		var idx = shuffle(getArr6());
		for (i = 0; i < idx.length; i++) {
			fst += (keys[idx[i]] + DL);
		}
		fst += change(idx);
		return fst;
	};
	
	 function hexfromdec(num) 
     {
       if (num > 65535) { 
             return ("err!")         
         }
         first = Math.round(num/4096 - .5); 
         temp1 = num - first * 4096; 
         second = Math.round(temp1/256 -.5); 
         temp2 = temp1 - second * 256; 
         third = Math.round(temp2/16 - .5); 
         fourth = temp2 - third * 16; 
         return (""+getletter(third)+getletter(fourth)); 
     };

     function getletter(num) {
         if (num < 10) {
             return num; 
         } else {
             if (num == 10) {    return "A";     }
             if (num == 11) {    return "B";     }
             if (num == 12) {    return "C";     }
             if (num == 13) { 	 return "D";	 }
             if (num == 14) {	 return "E";     }
             if (num == 15) {	 return "F";     }
         }
     }; 

     function encryptValue(x){
         var hex=''; var i; 
         for (i=0; i<x.length; i++) 
         { 
             hex += '%'+hexfromdec(x.charCodeAt(i)) 
         } 
         return hex; 
     };
	
     function encryptString(plainTextStr) {
    	 var randomNum = Math.random () + 0.3 // Adding 0.3 just for case if random number comes out as 0
         var str = Number(secretKey*randomNum).toString(16)+"";
         var encry = encryptValue(str);
         
         var ic = 1000;
         var ks = 128;

         var pp = encry;
         var iv = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
         var salt = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
         
         var aesUtil = new AesUtil(ks, ic);
         var ciphertext = aesUtil.encrypt(salt, iv, pp, plainTextStr);
         
         var finalStr =  getCipherText([ ciphertext, iv, salt, pp, ic, ks ]);
         
         return finalStr;
     };
