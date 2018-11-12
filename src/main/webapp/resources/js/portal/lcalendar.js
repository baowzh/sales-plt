function popupLunarCalendar() {
	var s = window.showModalDialog('component/LunarCalendar.html', null, 'dialogWidth: 680px; dialogHeight: 330px; resizable: no; help: no; status: no; scroll: no;')
	if (s) {
		var parts = s.split(',');
		return parts;
	}
	return null;
}

var nStr1 = new Array('��','һ','��','��','��','��','��','��','��','��','ʮ')
var nStr2 = new Array('��','ʮ','إ','ئ','��')
var nStr3 = new Array('','һ','��','��','��','��','��','��','��','��','ʮ','ʮһ','ʮ��')

function cDay(d){
   var s;

   switch (d) {
      case 10:
         s = '��ʮ'; break;
      case 20:
         s = '��ʮ'; break;
         break;
      case 30:
         s = '��ʮ'; break;
         break;
      default :
         s = nStr2[Math.floor(d/10)];
         s += nStr1[d%10];
   }
   return(s);
}

function descLunar(l) {
	var desc = '';
	if (l) {
		if (l.length == 8) {
			desc = nStr3[parseInt(l.substring(4,6),10)]+'��'+cDay(parseInt(l.substring(6,8),10));
		} else if (l.length >= 9) {
			desc = l.substring(8, 9)+nStr3[parseInt(l.substring(4,6),10)]+'��'+cDay(parseInt(l.substring(6,8),10));
		}
	}
	return desc;
}