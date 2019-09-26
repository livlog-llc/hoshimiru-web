//=========================================================================
// 日月出没計算 by「菊池さん」 <http://kikuchisan.net/>
//
// 機能　(1)日の出/日の入り時刻・方位角および南中時刻・高度
//       (2)月の出/月の入り時刻・方位角および南中時刻・高度
// を計算し表示します
//
// 引用図書
//  このスクリプトは「日の出・日の入りの計算」(長沢 工著 地人書館)の計算式
//  および定数を利用しています。
// 計算手順
//  (1)観測地の経緯度(lng,lat)とし、出没時刻 Dを仮定する
//  (2)時刻Dに対する赤経、赤緯(α,δ)、距離(r)、恒星時(Θ)を計算する(日出没)
//     時刻Dに対する赤経、赤緯(α,δ)、月視差(Π)、恒星時(Θ)を計算する(月出没)
//  (3)出没高度(k)を計算する
//  (4)出没点の時角(tk)を計算する。tkは出のときマイナス、入のときプラスにとる
//  (5)天体の時角(t)を計算し、時角の差dt=tk-tを計算する
//  (6)補正値dD=dt/⊿ の計算。太陽(⊿=360°),月(⊿=347.8°),惑星/恒星(⊿=361°)
//  (7)新しい仮定値をD+dDとしてこの計算を繰り返す。収束条件は｜dD｜<0.00005
//     収束時のD が出没時刻
//=========================================================================
// メインプログラム
// 引数　 .... 計算対象となる年月日　yy mm dd
// 　　　 .... 計算対象地点の経度、緯度、標高　lng lat hei
// 　　　 .... 表示フラグ-出没のみ(0)、出没+南中(1)、出没+南中+方位角(2)　disp
// 戻り値 .... なし。計算結果は以下の変数に格納されます
// 　　　　　　　日出時刻(Rs)、   日没時刻(Ss)、   南中時刻(Ds)
// 　　　　　　　日出方位角(ARs)、日没方位角(ASs)、南中高度(hs)
// 　　　　　　　月出時刻(Rm)、   月没時刻(Sm)、   南中時刻(Dm)
// 　　　　　　　月出方位角(ARm)、月没方位角(ASm)、南中高度(hm)
//=========================================================================
	pai = Math.PI / 180;
function SunMoon(yy,mm,dd,lng,lat,hei,disp) {
// グローバル変数
	converge = 0.00005;	//逐次近似計算収束判定値
	dT = ( 57 + 0.8 * (yy - 1990) ) / 86400;	//地球自転遅れ補正値(日)
	R = 0.585556;	//大気差
	E = 0.0353333 * Math.sqrt(hei);	//地平線伏角
	K = calc_K(yy-2000,mm,dd);	//2000年1月1日力学時正午からの経過日数(日)

	var Rs,Ss,Ds,hs,Rm,Sm,Dm,hm,ARs,ASs,ARm,ASm,T,rm_sun,rm_moon,bt_moon;
// 日の出/日の入り時刻、南中時刻および高度計算
	Rs = calc_SUN(lng,lat,0);	// 日出
	T = ( K + Rs + dT ) / 365.25;	//Rsの経過ユリウス年(日)
	rm_sun = lng_SUN(T);	//太陽黄経
	ARs = calc_AZ(rm_sun,0,T,Rs,lng,lat)+"°";	// 日出方位角
	Ss = calc_SUN(lng,lat,1);	// 日没
	T = ( K + Ss + dT ) / 365.25;	//Ssの経過ユリウス年(日)
	rm_sun = lng_SUN(T);	//太陽黄経
	ASs = calc_AZ(rm_sun,0,T,Ss,lng,lat)+"°";	// 日没方位角
	Ds = (Rs + Ss) / 2;	// 南中
	T = ( K + Ds + dT ) / 365.25;	//Dsの経過ユリウス年(日)
	rm_sun = lng_SUN(T);	//太陽黄経
	hs = calc_EL(rm_sun,0,T,Ds,lng,lat)+"°";	// 日南中高度
	Rs = num2jifun(Rs*24);
	Ss = num2jifun(Ss*24);
	Ds = num2jifun(Ds*24);
// 月の出/月の入り時刻、南中時刻および高度
	Rm = calc_MOON(lng,lat,0);	// 月出
	Sm = calc_MOON(lng,lat,1);	// 月没
	Dm = calc_MOON(lng,lat,2);	// 南中
	//出没なしの場合は--:--を表示
	if (Rm != "") {
		T = ( K + Rm + dT ) / 365.25;	//Rmの経過ユリウス年(日)
		rm_moon = lng_MOON(T);	//月黄経
		bt_moon = lat_MOON(T);	//月黄緯
		ARm = calc_AZ(rm_moon,bt_moon,T,Rm,lng,lat)+"°";	//月出方位角
		Rm = num2jifun(Rm*24);
	} else { Rm = "--:--"; ARm = "---"; }
	if (Sm != "") {
		T = ( K + Sm + dT ) / 365.25;	//Smの経過ユリウス年(日)
		rm_moon = lng_MOON(T);	//月黄経
		bt_moon = lat_MOON(T);	//月黄緯
		ASm = calc_AZ(rm_moon,bt_moon,T,Sm,lng,lat)+"°";	//月没方位角
		Sm = num2jifun(Sm*24);
	} else { Sm = "--:--"; ASm = "---";}
	if (Dm != "") {
		T = ( K + Dm + dT ) / 365.25;	//Dmの経過ユリウス年(日)
		rm_moon = lng_MOON(T);	//月黄経
		bt_moon = lat_MOON(T);	//月黄緯
		hm = calc_EL(rm_moon,bt_moon,T,Dm,lng,lat)+"°";	//月南中高度
		Dm = num2jifun(Dm*24);
	} else { Dm = "--:--"; hm = "---"; }
// 日出/日没表示
	document.write('<table bgcolor=white border=1 bordercolor=#c0c0c0 cellspacing=0><tr><td nowrap>');
	document.write('日出 <font color=blue>'+ Rs +'</font>');
	document.write('</td><td nowrap>');
	document.write('日没 <font color=blue>'+ Ss +'</font>');
if (disp > 1) {
	document.write('</td></tr><tr><td nowrap>');
	document.write('方位 <font color=blue>'+ ARs +'</font><br>');
	document.write('</td><td nowrap>');
	document.write('方位 <font color=blue>'+ ASs +'</font><br>');
}
if (disp > 0) {
	document.write('</td></tr><tr><td nowrap>');
	document.write('南中 <font color=blue>'+ Ds +'</font>');
	document.write('</td><td nowrap>');
	document.write('高度 <font color=blue>'+ hs +'</font>');
}
// 月出/月没表示
	document.write('</td></tr><tr><td nowrap>');
	document.write('月出 <font color=blue>'+ Rm +'</font>');
	document.write('</td><td nowrap>');
	document.write('月没 <font color=blue>'+ Sm +'</font>');
if (disp > 1) {
	document.write('</td></tr><tr><td nowrap>');
	document.write('方位 <font color=blue>'+ ARm +'</font><br>');
	document.write('</td><td nowrap>');
	document.write('方位 <font color=blue>'+ ASm +'</font><br>');
}
if (disp > 0) {
	document.write('</td></tr><tr><td nowrap>');
	document.write('南中 <font color=blue>'+ Dm +'</font>');
	document.write('</td><td nowrap>');
	document.write('高度 <font color=blue>'+ hm +'</font>');
}
	document.write('</td></tr></table>');
}
//=========================================================================
// 日の出/日の入り計算
// 引数　 .... 計算対象地点の経度、緯度　lng lat
// 　　　 .... 出没フラグ　flag(0)=出、flag(1)=没
// 戻り値 .... 出没時刻（0.xxxx日）
//=========================================================================
function calc_SUN(lng,lat,flag) {
	var ans = new Array();
	var T,rm_sun,r_sun,theta;
	var S,W,k,dt;
	var dD = 1;	//補正値初期値
	var D = 0.5;	//逐次計算初期時刻(日)

	while( Math.abs(dD) > converge ) {	// 逐次計算
		T = ( K + D + dT ) / 365.25;	//Dの経過ユリウス年(日)
		rm_sun = lng_SUN(T);	//太陽の黄経
		r_sun = dist_SUN(T);	//太陽の距離
		theta = calc_THETA(lng,T,D);	//恒星時
		Kou2Seki(rm_sun,0,T,ans);	//黄道 -> 赤道変換

		S = 0.266994 / r_sun;	//太陽の視半径
		W = 0.0024428 / r_sun;	//太陽の視差
		k = -S - R - E + W;	//太陽の出没高度
		dt = calc_dt(ans[0],ans[1],theta,k,lat,flag);	//時角差(dt=tk-t)計算
		dD = dt / 360;	//仮定時刻に対する補正値
		D = D + dD;
	}
	return D;
}
//=========================================================================
// 月の出/月の入り計算
// 引数　 .... 計算対象地点の経度、緯度　lng lat
// 　　　 .... 出没フラグ　flag(0)=出、flag(1)=没、flag(2)=南中
// 戻り値 .... 出没/南中時刻（0.xxxx日）
//=========================================================================
function calc_MOON(lng,lat,flag) {
	var ans = new Array();
	var T,rm_moon,bt_moon,theta;
	var W,k,dt;
	var dD = 1;	//補正値初期値
	var D = 0.5;	//逐次計算初期時刻(日)

	while( Math.abs(dD) > converge ) {	// 逐次計算
		T = ( K + D + dT ) / 365.25;	//Dの経過ユリウス年(日)
		rm_moon = lng_MOON(T);	//月の黄経
		bt_moon = lat_MOON(T);	//月の黄緯
		theta = calc_THETA(lng,T,D);	//恒星時
		Kou2Seki(rm_moon,bt_moon,T,ans);

		if (flag != 2) {	//南中のときは計算しない
			W = para_MOON(T);	//月の視差
			k = - R - E + W;	//月の出没高度
		}
		dt = calc_dt(ans[0],ans[1],theta,k,lat,flag);	//時角差(dt=tk-t)計算
		dD = dt / 347.8;	//仮定時刻に対する補正値
		D = D + dD;
	}
	if (D < 0 || D >= 1) { D = ""; }	//出没なし
	return D;
}
//=========================================================================
// 時刻(D)における黄経、黄緯(λ(T),β(T))の天体の方位角(A)計算
// 引数　 .... 天体の黄経、黄緯(λ(T),β(T))(度)　ramda,beta
// 　　　 .... J2000.0からの経過ユリウス年(x.xxx日)　T
// 　　　 .... 日本時によるその日０時からの経過時刻(0.xxx日)　D
// 　　　 .... 観測地点の経度、緯度　lng,lat
// 戻り値 .... 高度(xx.x度)
//=========================================================================
function calc_AZ(ramda,beta,T,D,lng,lat) {
	var ans = new Array();
	Kou2Seki(ramda,beta,T,ans);	//黄道 -> 赤道変換
	return calc_AZe(ans[0],ans[1],T,D,lng,lat) ;
}
//=========================================================================
// 時刻(D)における赤経、赤緯(α(T),δ(T))(度)の天体の方位角(A)計算
//=========================================================================
function calc_AZe(alpha,delta,T,D,lng,lat) {
	var theta,t,A,A0,A1;
	theta = calc_THETA(lng,T,D);	//恒星時
	t = theta - alpha;	//天体の時角
	with(Math) {
	A0 =-cos(pai * delta) * sin(pai * t);
	A1 = sin(pai * delta) * cos(pai * lat) - cos(pai * delta) * sin(pai * lat) * cos(pai * t);
	A  = atan(A0 / A1) / pai;
	if (A1 > 0 && A < 0) {  A = 360 + A; }	//分母がプラスのときは-90<A<90
	if (A1 < 0) {  A += 180; }	//分母がマイナスのときは90<A<270 -> +180°
	A = ( round(A * 10) ) / 10;
	}
	return A ;
}
//=========================================================================
// 時刻(D)における黄経、黄緯(λ(T),β(T))の天体の高度(h)計算
// 引数　 .... 天体の黄経、黄緯(λ(T),β(T))(度)　ramda,beta
// 　　　 .... J2000.0からの経過ユリウス年(x.xxx日)　T
// 　　　 .... 日本時によるその日０時からの経過時刻(0.xxx日)　D
// 　　　 .... 観測地点の経度、緯度　lng,lat
// 戻り値 .... 高度(xx.x度)
//=========================================================================
function calc_EL(ramda,beta,T,D,lng,lat) {
	var ans = new Array();
	Kou2Seki(ramda,beta,T,ans);	//黄道 -> 赤道変換
	return calc_ELe(ans[0],ans[1],T,D,lng,lat);
}
//=========================================================================
// 時刻(D)における赤経、赤緯(α(T),δ(T))(度)の天体の高度(h)計算
//=========================================================================
function calc_ELe(alpha,delta,T,D,lng,lat) {
	var theta,t,h;
	theta = calc_THETA(lng,T,D);	//恒星時
	t = theta - alpha;	//天体の時角
	with(Math) {
	h  = sin(pai * delta) * sin(pai * lat);
	h += cos(pai * delta) * cos(pai * lat) * cos(pai * t);
	h  = asin(h) / pai;

	h += 0.0167 / tan( pai * (h + 8.6/(h + 4.4)) );	// 大気差補正
	h = ( round(h * 10) ) / 10;
	}
	return h ;
}
//=========================================================================
// 出没点(k)の時角(tk)と天体の時角(t)との差(dt=tk-t)を計算する
// 引数　 .... 天体の赤経、赤緯(α(T),δ(T))(度)　alpha,delta
// 　　　 .... 観測地点の出没高度(度)、恒星時Θ(度)、緯度(度)　k,theta,lat
// 　　　 .... 出没フラグ　flag(0)=出、flag(1)=没、flag(2)=南中
// 戻り値 .... 時角の差　dt
//=========================================================================
function calc_dt(alpha,delta,theta,k,lat,flag) {
	var dt,tk,t;
	if (flag == 2) { tk = 0; }	//南中の場合は天体の時角を返す
	else {
		with(Math) {
		tk  = sin(pai * k) - sin(pai * delta) * sin(pai * lat);
		tk /= cos(pai * delta) * cos(pai * lat);
		tk  = acos(tk) / pai;	//出没点の時角
		}
		// tkは出のときマイナス、没のときプラス
		if(flag == 0 && tk > 0) { tk = -tk; }
		if(flag == 1 && tk < 0) { tk = -tk; }
	}
	t = theta - alpha;	//天体の時角
	dt = tk - t;
	// dtの絶対値を180°以下に調整
	if (dt >  180) { while ( dt >  180 ) { dt -= 360; } }
	if (dt < -180) { while ( dt < -180 ) { dt += 360; } }
	return dt ;
}
//=========================================================================
// 2000年1月1日力学時正午からの経過日数(K)計算
// 引数　 .... (2000+YY)年MM月DD日0時(地方時:I=9)　YY,MM,DD
// 戻り値 .... J2000.0(2000年1月1日力学時正午)からの経過日数(K日)
//=========================================================================
function calc_K(yy,mm,dd) {
	var YY,MM,DD,K;
	YY = yy; MM = mm; DD = dd;
	// 1月、2月の場合は前年の13月、14月とみなして計算
	if( MM < 3.0 ){ YY -= 1.0; MM += 12.0; }

	K  = 365*YY + 30*MM + DD - 33.5 - 9/24;	//地方時:I=9
	K += Math.floor( 3*(MM + 1) / 5 );
	K += Math.floor( YY / 4 );
	return K ;
}
//=========================================================================
// 観測地点の恒星時Θ(度)の計算
// 引数　 .... 観測地点の経度(度)　lng
// 　　　 .... J2000.0からの経過ユリウス年(力学時)(x.xxx日)　T
// 　　　 .... 日本時によるその日０時からの経過時間(力学時)(0.xxx日)　D
// 戻り値 .... 観測地点の恒星時Θ(度)
//=========================================================================
function calc_THETA(lng,T,D) {
	return norm(325.4606 + 360.007700536*T + 0.00000003879*T*T + 360*D + lng);
}
//=========================================================================
// 黄道座標 -> 赤道座標変換（T は力学時)
// 引数　 .... 黄経、黄緯(λ(T),β(T))(度)　ramda,beta
// 戻り値 .... 赤経、赤緯(α(T),δ(T))(度)　ans[0],ans[1]
//=========================================================================
function Kou2Seki(ramda,beta,T,ans) {
	var U,V,W;
	var e = (23.439291 - .000130042 * T) * pai;	//黄道傾角
	var rm = ramda * pai;
	var bt = beta * pai;
	with(Math) {
	U = cos( bt ) * cos( rm );
	V =-sin( bt ) * sin( e ) + cos( bt ) * sin( rm ) * cos( e );
	W = sin( bt ) * cos( e ) + cos( bt ) * sin( rm ) * sin( e );
	ans[0] = V / U;
	ans[1] = W / sqrt( U * U + V * V );
	ans[0] = atan( ans[0] ) / pai;
	if (U < 0) ans[0] += 180;	//Uがマイナスのときは90<α<270 -> +180°
	ans[1] = atan( ans[1] ) / pai;
	}
}
//=========================================================================
// 角度の正規化を行う。すなわち引数の範囲を 0≦θ＜360 にする。
//=========================================================================
function norm(angle) {
	return angle - 360 * Math.floor(angle / 360);
}
//=========================================================================
// 時間：数値->時間：時分変換(xx.xxxx -> hh:mm)
//=========================================================================
function num2jifun(num) {
	var num1,num2;
	num1 = Math.floor( num );	//整数部(=時)
	num2 = ( num - num1 ) * 60;	//小数点部(=分)
	num2 = Math.round( num2 );
	if ( num2 == 60 ) { num1 += 1; num2 -= 60; }
	if ( num2 < 10 )  { num2 = "0" + num2; }
	return ( num1 + ":" + num2 );
}
//=========================================================================
// 経緯度：度分->経緯度：数値変換(dd:ff -> xx.xxxx)
//=========================================================================
function dofun2num(dofun) {
	var str = new Array();
	str = dofun.split(":");
	str[0] = str[0]*1; str[1] = str[1]*1;	//*1は文字を数値化
	if ( str[0] < 0 ) { str[1] = -str[1]; }	//符号(マイナス)処理
	return str[0] + str[1] / 60;
}
//=========================================================================
// 太陽の黄経 λsun(T) を計算する（T は力学時）
//=========================================================================
function lng_SUN(T) {
	var rm_sun;
	with(Math) {
	rm_sun  = .0003 * sin( pai * norm( 329.7 + 44.43 * T ) );
	rm_sun += .0003 * sin( pai * norm( 352.5 + 1079.97 * T ) );
	rm_sun += .0004 * sin( pai * norm( 21.1 + 720.02 * T ) );
	rm_sun += .0004 * sin( pai * norm( 157.3 + 299.30 * T ) );
	rm_sun += .0004 * sin( pai * norm( 234.9 + 315.56 * T ) );
	rm_sun += .0005 * sin( pai * norm( 291.2 + 22.81 * T ) );
	rm_sun += .0005 * sin( pai * norm( 207.4 + 1.50 * T ) );
	rm_sun += .0006 * sin( pai * norm( 29.8 + 337.18 * T ) );
	rm_sun += .0007 * sin( pai * norm( 206.8 + 30.35 * T ) );
	rm_sun += .0007 * sin( pai * norm( 153.3 + 90.38 * T ) );
	rm_sun += .0008 * sin( pai * norm( 132.5 + 659.29 * T ) );
	rm_sun += .0013 * sin( pai * norm( 81.4 + 225.18 * T ) );
	rm_sun += .0015 * sin( pai * norm( 343.2 + 450.37 * T ) );
	rm_sun += .0018 * sin( pai * norm( 251.3 + 0.20 * T ) );
	rm_sun += .0018 * sin( pai * norm( 297.8 + 4452.67 * T ) );
	rm_sun += .0020 * sin( pai * norm( 247.1 + 329.64 * T ) );
	rm_sun += .0048 * sin( pai * norm( 234.95 + 19.341 * T ) );
	rm_sun += .0200 * sin( pai * norm( 355.05 + 719.981 * T ) );
	rm_sun += (1.9146 - .00005 * T) * sin( pai * norm( 357.538 + 359.991 * T ) );
	rm_sun += norm( 280.4603 + 360.00769 * T );
	}
	return rm_sun;
}
//=========================================================================
// 太陽の距離 r(T) を計算する（T は力学時）
//=========================================================================
function dist_SUN(T) {
	var r_sun;
	with(Math) {
	r_sun  = .000007 * sin( pai * norm( 156 + 329.6 * T ) );
	r_sun += .000007 * sin( pai * norm( 254 + 450.4 * T ) );
	r_sun += .000013 * sin( pai * norm( 27.8 + 4452.67 * T ) );
	r_sun += .000030 * sin( pai * norm( 90.0 ) );
	r_sun += .000091 * sin( pai * norm( 265.1 + 719.98 * T ) );
	r_sun += (.007256 - .0000002 * T) * sin( pai * norm( 267.54 + 359.991 * T ) ) ;
	r_sun = pow(10,r_sun);
	}
	return r_sun;
}
//=========================================================================
// 月の黄経 λmoon(T) を計算する（T は力学時）
//=========================================================================
function lng_MOON(T) {
	var rm_moon,Am;
	with(Math) {
	Am  = .0006 * sin( pai * norm( 54 + 19.3 * T ) );
	Am += .0006 * sin( pai * norm( 71 + 0.2 * T ) );
	Am += .0020 * sin( pai * norm( 55.0 + 19.34 * T ) );
	Am += .0040 * sin( pai * norm( 119.5 + 1.33 * T ) );

	rm_moon  = .0003 * sin( pai * norm( 280 + 23221.3 * T ) );
	rm_moon += .0003 * sin( pai * norm( 161 + 40.7 * T ) );
	rm_moon += .0003 * sin( pai * norm( 311 + 5492.0 * T ) );
	rm_moon += .0003 * sin( pai * norm( 147 + 18089.3 * T ) );
	rm_moon += .0003 * sin( pai * norm( 66 + 3494.7 * T ) );
	rm_moon += .0003 * sin( pai * norm( 83 + 3814.0 * T ) );
	rm_moon += .0004 * sin( pai * norm( 20 + 720.0 * T ) );
	rm_moon += .0004 * sin( pai * norm( 71 + 9584.7 * T ) );
	rm_moon += .0004 * sin( pai * norm( 278 + 120.1 * T ) );
	rm_moon += .0004 * sin( pai * norm( 313 + 398.7 * T ) );
	rm_moon += .0005 * sin( pai * norm( 332 + 5091.3 * T ) );
	rm_moon += .0005 * sin( pai * norm( 114 + 17450.7 * T ) );
	rm_moon += .0005 * sin( pai * norm( 181 + 19088.0 * T ) );
	rm_moon += .0005 * sin( pai * norm( 247 + 22582.7 * T ) );
	rm_moon += .0006 * sin( pai * norm( 128 + 1118.7 * T ) );
	rm_moon += .0007 * sin( pai * norm( 216 + 278.6 * T ) );
	rm_moon += .0007 * sin( pai * norm( 275 + 4853.3 * T ) );
	rm_moon += .0007 * sin( pai * norm( 140 + 4052.0 * T ) );
	rm_moon += .0008 * sin( pai * norm( 204 + 7906.7 * T ) );
	rm_moon += .0008 * sin( pai * norm( 188 + 14037.3 * T ) );
	rm_moon += .0009 * sin( pai * norm( 218 + 8586.0 * T ) );
	rm_moon += .0011 * sin( pai * norm( 276.5 + 19208.02 * T ) );
	rm_moon += .0012 * sin( pai * norm( 339.0 + 12678.71 * T ) );
	rm_moon += .0016 * sin( pai * norm( 242.2 + 18569.38 * T ) );
	rm_moon += .0018 * sin( pai * norm( 4.1 + 4013.29 * T ) );
	rm_moon += .0020 * sin( pai * norm( 55.0 + 19.34 * T ) );
	rm_moon += .0021 * sin( pai * norm( 105.6 + 3413.37 * T ) );
	rm_moon += .0021 * sin( pai * norm( 175.1 + 719.98 * T ) );
	rm_moon += .0021 * sin( pai * norm( 87.5 + 9903.97 * T ) );
	rm_moon += .0022 * sin( pai * norm( 240.6 + 8185.36 * T ) );
	rm_moon += .0024 * sin( pai * norm( 252.8 + 9224.66 * T ) );
	rm_moon += .0024 * sin( pai * norm( 211.9 + 988.63 * T ) );
	rm_moon += .0026 * sin( pai * norm( 107.2 + 13797.39 * T ) );
	rm_moon += .0027 * sin( pai * norm( 272.5 + 9183.99 * T ) );
	rm_moon += .0037 * sin( pai * norm( 349.1 + 5410.62 * T ) );
	rm_moon += .0039 * sin( pai * norm( 111.3 + 17810.68 * T ) );
	rm_moon += .0040 * sin( pai * norm( 119.5 + 1.33 * T ) );
	rm_moon += .0040 * sin( pai * norm( 145.6 + 18449.32 * T ) );
	rm_moon += .0040 * sin( pai * norm( 13.2 + 13317.34 * T ) );
	rm_moon += .0048 * sin( pai * norm( 235.0 + 19.34 * T ) );
	rm_moon += .0050 * sin( pai * norm( 295.4 + 4812.66 * T ) );
	rm_moon += .0052 * sin( pai * norm( 197.2 + 319.32 * T ) );
	rm_moon += .0068 * sin( pai * norm( 53.2 + 9265.33 * T ) );
	rm_moon += .0079 * sin( pai * norm( 278.2 + 4493.34 * T ) );
	rm_moon += .0085 * sin( pai * norm( 201.5 + 8266.71 * T ) );
	rm_moon += .0100 * sin( pai * norm( 44.89 + 14315.966 * T ) );
	rm_moon += .0107 * sin( pai * norm( 336.44 + 13038.696 * T ) );
	rm_moon += .0110 * sin( pai * norm( 231.59 + 4892.052 * T ) );
	rm_moon += .0125 * sin( pai * norm( 141.51 + 14436.029 * T ) );
	rm_moon += .0153 * sin( pai * norm( 130.84 + 758.698 * T ) );
	rm_moon += .0305 * sin( pai * norm( 312.49 + 5131.979 * T ) );
	rm_moon += .0348 * sin( pai * norm( 117.84 + 4452.671 * T ) );
	rm_moon += .0410 * sin( pai * norm( 137.43 + 4411.998 * T ) );
	rm_moon += .0459 * sin( pai * norm( 238.18 + 8545.352 * T ) );
	rm_moon += .0533 * sin( pai * norm( 10.66 + 13677.331 * T ) );
	rm_moon += .0572 * sin( pai * norm( 103.21 + 3773.363 * T ) );
	rm_moon += .0588 * sin( pai * norm( 214.22 + 638.635 * T ) );
	rm_moon += .1143 * sin( pai * norm( 6.546 + 9664.0404 * T ) );
	rm_moon += .1856 * sin( pai * norm( 177.525 + 359.9905 * T ) );
	rm_moon += .2136 * sin( pai * norm( 269.926 + 9543.9773 * T ) );
	rm_moon += .6583 * sin( pai * norm( 235.700 + 8905.3422 * T ) );
	rm_moon += 1.2740 * sin( pai * norm( 100.738 + 4133.3536 * T ) );
	rm_moon += 6.2887 * sin( pai * norm( 134.961 + 4771.9886 * T + Am ) );
	rm_moon += norm( 218.3161 + 4812.67881 * T );
	}
	return rm_moon;
}
//=========================================================================
// 月の黄緯 βmoon(T) を計算する（T は力学時）
//=========================================================================
function lat_MOON(T) {
	var bt_moon,Bm;
	with(Math) {
	Bm  = .0005 * sin( pai * norm( 307 + 19.4 * T ) );
	Bm += .0026 * sin( pai * norm( 55.0 + 19.34 * T ) );
	Bm += .0040 * sin( pai * norm( 119.5 + 1.33 * T ) );
	Bm += .0043 * sin( pai * norm( 322.1 + 19.36 * T ) );
	Bm += .0267 * sin( pai * norm( 234.95 + 19.341 * T ) );

	bt_moon  =  .0003 * sin( pai * norm( 234 + 19268.0 * T ) );
	bt_moon +=  .0003 * sin( pai * norm( 146 + 3353.3 * T ) );
	bt_moon +=  .0003 * sin( pai * norm( 107 + 18149.4 * T ) );
	bt_moon +=  .0003 * sin( pai * norm( 205 + 22642.7 * T ) );
	bt_moon +=  .0004 * sin( pai * norm( 147 + 14097.4 * T ) );
	bt_moon +=  .0004 * sin( pai * norm( 13 + 9325.4 * T ) );
	bt_moon +=  .0004 * sin( pai * norm( 81 + 10242.6 * T ) );
	bt_moon +=  .0004 * sin( pai * norm( 238 + 23281.3 * T ) );
	bt_moon +=  .0004 * sin( pai * norm( 311 + 9483.9 * T ) );
	bt_moon +=  .0005 * sin( pai * norm( 239 + 4193.4 * T ) );
	bt_moon +=  .0005 * sin( pai * norm( 280 + 8485.3 * T ) );
	bt_moon +=  .0006 * sin( pai * norm( 52 + 13617.3 * T ) );
	bt_moon +=  .0006 * sin( pai * norm( 224 + 5590.7 * T ) );
	bt_moon +=  .0007 * sin( pai * norm( 294 + 13098.7 * T ) );
	bt_moon +=  .0008 * sin( pai * norm( 326 + 9724.1 * T ) );
	bt_moon +=  .0008 * sin( pai * norm( 70 + 17870.7 * T ) );
	bt_moon +=  .0010 * sin( pai * norm( 18.0 + 12978.66 * T ) );
	bt_moon +=  .0011 * sin( pai * norm( 138.3 + 19147.99 * T ) );
	bt_moon +=  .0012 * sin( pai * norm( 148.2 + 4851.36 * T ) );
	bt_moon +=  .0012 * sin( pai * norm( 38.4 + 4812.68 * T ) );
	bt_moon +=  .0013 * sin( pai * norm( 155.4 + 379.35 * T ) );
	bt_moon +=  .0013 * sin( pai * norm( 95.8 + 4472.03 * T ) );
	bt_moon +=  .0014 * sin( pai * norm( 219.2 + 299.96 * T ) );
	bt_moon +=  .0015 * sin( pai * norm( 45.8 + 9964.00 * T ) );
	bt_moon +=  .0015 * sin( pai * norm( 211.1 + 9284.69 * T ) );
	bt_moon +=  .0016 * sin( pai * norm( 135.7 + 420.02 * T ) );
	bt_moon +=  .0017 * sin( pai * norm( 99.8 + 14496.06 * T ) );
	bt_moon +=  .0018 * sin( pai * norm( 270.8 + 5192.01 * T ) );
	bt_moon +=  .0018 * sin( pai * norm( 243.3 + 8206.68 * T ) );
	bt_moon +=  .0019 * sin( pai * norm( 230.7 + 9244.02 * T ) );
	bt_moon +=  .0021 * sin( pai * norm( 170.1 + 1058.66 * T ) );
	bt_moon +=  .0022 * sin( pai * norm( 331.4 + 13377.37 * T ) );
	bt_moon +=  .0025 * sin( pai * norm( 196.5 + 8605.38 * T ) );
	bt_moon +=  .0034 * sin( pai * norm( 319.9 + 4433.31 * T ) );
	bt_moon +=  .0042 * sin( pai * norm( 103.9 + 18509.35 * T ) );
	bt_moon +=  .0043 * sin( pai * norm( 307.6 + 5470.66 * T ) );
	bt_moon +=  .0082 * sin( pai * norm( 144.9 + 3713.33 * T ) );
	bt_moon +=  .0088 * sin( pai * norm( 176.7 + 4711.96 * T ) );
	bt_moon +=  .0093 * sin( pai * norm( 277.4 + 8845.31 * T ) );
	bt_moon +=  .0172 * sin( pai * norm( 3.18 + 14375.997 * T ) );
	bt_moon +=  .0326 * sin( pai * norm( 328.96 + 13737.362 * T ) );
	bt_moon +=  .0463 * sin( pai * norm( 172.55 + 698.667 * T ) );
	bt_moon +=  .0554 * sin( pai * norm( 194.01 + 8965.374 * T ) );
	bt_moon +=  .1732 * sin( pai * norm( 142.427 + 4073.3220 * T ) );
	bt_moon +=  .2777 * sin( pai * norm( 138.311 + 60.0316 * T ) );
	bt_moon +=  .2806 * sin( pai * norm( 228.235 + 9604.0088 * T ) );
	bt_moon +=  5.1282 * sin( pai * norm( 93.273 + 4832.0202 * T + Bm ) );
	}
	return bt_moon;
}
//=========================================================================
// 月の視差 Π(T) を計算する（T は力学時）
//=========================================================================
function para_MOON(T) {
	var p_moon;
	with(Math) {
	p_moon  =  .0003 * sin( pai * norm( 227 + 4412.0 * T ) );
	p_moon +=  .0004 * sin( pai * norm( 194 + 3773.4 * T ) );
	p_moon +=  .0005 * sin( pai * norm( 329 + 8545.4 * T ) );
	p_moon +=  .0009 * sin( pai * norm( 100.0 + 13677.3 * T ) );
	p_moon +=  .0028 * sin( pai * norm( 0.0 + 9543.98 * T ) );
	p_moon +=  .0078 * sin( pai * norm( 325.7 + 8905.34 * T ) );
	p_moon +=  .0095 * sin( pai * norm( 190.7 + 4133.35 * T ) );
	p_moon +=  .0518 * sin( pai * norm( 224.98 + 4771.989 * T ) );
	p_moon +=  .9507 * sin( pai * norm( 90 ) );
	}
	return p_moon;
}
//=========================================================================
// 日月出没計算の都市(経緯度/標高)選択
// 引数　 .... なし
// 戻り値 .... なし
//=========================================================================
var city = new Array("クリア","稚内","旭川","札幌","根室","釧路","函館","青森","秋田","盛岡","仙台","山形","福島","宇都宮","前橋","水戸","浦和","東京","千葉","横浜","小笠原","新潟","富山","長野","金沢","福井","甲府","岐阜","名古屋","静岡","富士山","京都","大津","津","奈良","大阪","神戸","和歌山","鳥取","松江","岡山","広島","山口","高松","徳島","松山","高知","福岡","佐賀","大分","熊本","長崎","宮崎","鹿児島","那覇");
var tokei = new Array("0:0","141:41","142:22","141:21","145:35","144:24","140:45","140:44","140:07","141:09","140:52","140:21","140:28","139:53","139:04","140:29","139:39","139:45","140:07","139:39","142:11","139:02","137:13","138:11","136:39","136:13","138:34","136:46","136:55","138:23","138:44","135:45","135:52","136:31","135:50","135:29","135:11","135:10","134:14","133:03","133:56","132:27","131:28","134:03","134:33","132:46","133:32","130:24","130:18","131:37","130:43","129:52","131:25","130:33","127:40");
var hokui = new Array("0:0","45:25","43:36","43:04","43:20","42:59","41:49","40:49","39:43","39:42","38:16","38:15","37:45","36:34","36:23","36:22","35:51","35:39","35:36","35:27","27:05","37:55","36:41","36:39","36:34","36:04","35:40","35:25","35:10","34:58","35:21","35:01","35:00","34:44","34:41","34:41","34:41","34:14","35:30","35:28","34:40","34:23","34:11","34:21","34:04","33:50","33:33","33:35","33:15","33:14","32:48","32:45","31:54","31:36","26:13");
var hyoko = new Array("0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","3776","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0");

function citySelect(form, sel){
	c_city = sel.options[sel.selectedIndex].value;
	if(c_city == "クリア") {
		DeleteCookie("〇〇〇_koyomi"); alert('地域を初期値「'+city[idx]+'」に戻します');
	} else {
		for (i = 0; i < city.length; i++) {
			if(c_city == city[i]) {
				if(navigator.appName=="Netscape" && navigator.appVersion.substring(0,4)>=4.05){ c_city = escape(city[i]); } else { c_city = city[i]; }
				c_tokei = tokei[i]; c_hokui = hokui[i]; c_hyoko = hyoko[i];
			}
		}
		set_data=c_city+","+c_tokei+","+c_hokui+","+c_hyoko;
		cookie_name="〇〇〇_koyomi";
		setCookieData(set_data);
		alert('地域を「'+unescape(c_city)+'」に変更します。「クリア」で初期値に戻ります。');
	}
	location.reload();
}
//=========================================================================
// うるう年チェック
//=========================================================================
function leap(year) {
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
		{ monthDays[1] = 29; } else { monthDays[1] = 28; }
}
