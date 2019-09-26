//=========================================================================
// �����o�v�v�Z by�u�e�r����v <http://kikuchisan.net/>
//
// �@�\�@(1)���̏o/���̓��莞���E���ʊp����ѓ쒆�����E���x
//       (2)���̏o/���̓��莞���E���ʊp����ѓ쒆�����E���x
// ���v�Z���\�����܂�
//
// ���p�}��
//  ���̃X�N���v�g�́u���̏o�E���̓���̌v�Z�v(���� �H�� �n�l����)�̌v�Z��
//  ����ђ萔�𗘗p���Ă��܂��B
// �v�Z�菇
//  (1)�ϑ��n�̌o�ܓx(lng,lat)�Ƃ��A�o�v���� D�����肷��
//  (2)����D�ɑ΂���Ԍo�A�Ԉ�(��,��)�A����(r)�A�P����(��)���v�Z����(���o�v)
//     ����D�ɑ΂���Ԍo�A�Ԉ�(��,��)�A������(��)�A�P����(��)���v�Z����(���o�v)
//  (3)�o�v���x(k)���v�Z����
//  (4)�o�v�_�̎��p(tk)���v�Z����Btk�͏o�̂Ƃ��}�C�i�X�A���̂Ƃ��v���X�ɂƂ�
//  (5)�V�̂̎��p(t)���v�Z���A���p�̍�dt=tk-t���v�Z����
//  (6)�␳�ldD=dt/�� �̌v�Z�B���z(��=360��),��(��=347.8��),�f��/�P��(��=361��)
//  (7)�V��������l��D+dD�Ƃ��Ă��̌v�Z���J��Ԃ��B���������́bdD�b<0.00005
//     ��������D ���o�v����
//=========================================================================
// ���C���v���O����
// �����@ .... �v�Z�ΏۂƂȂ�N�����@yy mm dd
// �@�@�@ .... �v�Z�Ώےn�_�̌o�x�A�ܓx�A�W���@lng lat hei
// �@�@�@ .... �\���t���O-�o�v�̂�(0)�A�o�v+�쒆(1)�A�o�v+�쒆+���ʊp(2)�@disp
// �߂�l .... �Ȃ��B�v�Z���ʂ͈ȉ��̕ϐ��Ɋi�[����܂�
// �@�@�@�@�@�@�@���o����(Rs)�A   ���v����(Ss)�A   �쒆����(Ds)
// �@�@�@�@�@�@�@���o���ʊp(ARs)�A���v���ʊp(ASs)�A�쒆���x(hs)
// �@�@�@�@�@�@�@���o����(Rm)�A   ���v����(Sm)�A   �쒆����(Dm)
// �@�@�@�@�@�@�@���o���ʊp(ARm)�A���v���ʊp(ASm)�A�쒆���x(hm)
//=========================================================================
	pai = Math.PI / 180;
function SunMoon(yy,mm,dd,lng,lat,hei,disp) {
// �O���[�o���ϐ�
	converge = 0.00005;	//�����ߎ��v�Z��������l
	dT = ( 57 + 0.8 * (yy - 1990) ) / 86400;	//�n�����]�x��␳�l(��)
	R = 0.585556;	//��C��
	E = 0.0353333 * Math.sqrt(hei);	//�n�������p
	K = calc_K(yy-2000,mm,dd);	//2000�N1��1���͊w�����߂���̌o�ߓ���(��)

	var Rs,Ss,Ds,hs,Rm,Sm,Dm,hm,ARs,ASs,ARm,ASm,T,rm_sun,rm_moon,bt_moon;
// ���̏o/���̓��莞���A�쒆��������э��x�v�Z
	Rs = calc_SUN(lng,lat,0);	// ���o
	T = ( K + Rs + dT ) / 365.25;	//Rs�̌o�߃����E�X�N(��)
	rm_sun = lng_SUN(T);	//���z���o
	ARs = calc_AZ(rm_sun,0,T,Rs,lng,lat)+"��";	// ���o���ʊp
	Ss = calc_SUN(lng,lat,1);	// ���v
	T = ( K + Ss + dT ) / 365.25;	//Ss�̌o�߃����E�X�N(��)
	rm_sun = lng_SUN(T);	//���z���o
	ASs = calc_AZ(rm_sun,0,T,Ss,lng,lat)+"��";	// ���v���ʊp
	Ds = (Rs + Ss) / 2;	// �쒆
	T = ( K + Ds + dT ) / 365.25;	//Ds�̌o�߃����E�X�N(��)
	rm_sun = lng_SUN(T);	//���z���o
	hs = calc_EL(rm_sun,0,T,Ds,lng,lat)+"��";	// ���쒆���x
	Rs = num2jifun(Rs*24);
	Ss = num2jifun(Ss*24);
	Ds = num2jifun(Ds*24);
// ���̏o/���̓��莞���A�쒆��������э��x
	Rm = calc_MOON(lng,lat,0);	// ���o
	Sm = calc_MOON(lng,lat,1);	// ���v
	Dm = calc_MOON(lng,lat,2);	// �쒆
	//�o�v�Ȃ��̏ꍇ��--:--��\��
	if (Rm != "") {
		T = ( K + Rm + dT ) / 365.25;	//Rm�̌o�߃����E�X�N(��)
		rm_moon = lng_MOON(T);	//�����o
		bt_moon = lat_MOON(T);	//������
		ARm = calc_AZ(rm_moon,bt_moon,T,Rm,lng,lat)+"��";	//���o���ʊp
		Rm = num2jifun(Rm*24);
	} else { Rm = "--:--"; ARm = "---"; }
	if (Sm != "") {
		T = ( K + Sm + dT ) / 365.25;	//Sm�̌o�߃����E�X�N(��)
		rm_moon = lng_MOON(T);	//�����o
		bt_moon = lat_MOON(T);	//������
		ASm = calc_AZ(rm_moon,bt_moon,T,Sm,lng,lat)+"��";	//���v���ʊp
		Sm = num2jifun(Sm*24);
	} else { Sm = "--:--"; ASm = "---";}
	if (Dm != "") {
		T = ( K + Dm + dT ) / 365.25;	//Dm�̌o�߃����E�X�N(��)
		rm_moon = lng_MOON(T);	//�����o
		bt_moon = lat_MOON(T);	//������
		hm = calc_EL(rm_moon,bt_moon,T,Dm,lng,lat)+"��";	//���쒆���x
		Dm = num2jifun(Dm*24);
	} else { Dm = "--:--"; hm = "---"; }
// ���o/���v�\��
	document.write('<table bgcolor=white border=1 bordercolor=#c0c0c0 cellspacing=0><tr><td nowrap>');
	document.write('���o <font color=blue>'+ Rs +'</font>');
	document.write('</td><td nowrap>');
	document.write('���v <font color=blue>'+ Ss +'</font>');
if (disp > 1) {
	document.write('</td></tr><tr><td nowrap>');
	document.write('���� <font color=blue>'+ ARs +'</font><br>');
	document.write('</td><td nowrap>');
	document.write('���� <font color=blue>'+ ASs +'</font><br>');
}
if (disp > 0) {
	document.write('</td></tr><tr><td nowrap>');
	document.write('�쒆 <font color=blue>'+ Ds +'</font>');
	document.write('</td><td nowrap>');
	document.write('���x <font color=blue>'+ hs +'</font>');
}
// ���o/���v�\��
	document.write('</td></tr><tr><td nowrap>');
	document.write('���o <font color=blue>'+ Rm +'</font>');
	document.write('</td><td nowrap>');
	document.write('���v <font color=blue>'+ Sm +'</font>');
if (disp > 1) {
	document.write('</td></tr><tr><td nowrap>');
	document.write('���� <font color=blue>'+ ARm +'</font><br>');
	document.write('</td><td nowrap>');
	document.write('���� <font color=blue>'+ ASm +'</font><br>');
}
if (disp > 0) {
	document.write('</td></tr><tr><td nowrap>');
	document.write('�쒆 <font color=blue>'+ Dm +'</font>');
	document.write('</td><td nowrap>');
	document.write('���x <font color=blue>'+ hm +'</font>');
}
	document.write('</td></tr></table>');
}
//=========================================================================
// ���̏o/���̓���v�Z
// �����@ .... �v�Z�Ώےn�_�̌o�x�A�ܓx�@lng lat
// �@�@�@ .... �o�v�t���O�@flag(0)=�o�Aflag(1)=�v
// �߂�l .... �o�v�����i0.xxxx���j
//=========================================================================
function calc_SUN(lng,lat,flag) {
	var ans = new Array();
	var T,rm_sun,r_sun,theta;
	var S,W,k,dt;
	var dD = 1;	//�␳�l�����l
	var D = 0.5;	//�����v�Z��������(��)

	while( Math.abs(dD) > converge ) {	// �����v�Z
		T = ( K + D + dT ) / 365.25;	//D�̌o�߃����E�X�N(��)
		rm_sun = lng_SUN(T);	//���z�̉��o
		r_sun = dist_SUN(T);	//���z�̋���
		theta = calc_THETA(lng,T,D);	//�P����
		Kou2Seki(rm_sun,0,T,ans);	//���� -> �ԓ��ϊ�

		S = 0.266994 / r_sun;	//���z�̎����a
		W = 0.0024428 / r_sun;	//���z�̎���
		k = -S - R - E + W;	//���z�̏o�v���x
		dt = calc_dt(ans[0],ans[1],theta,k,lat,flag);	//���p��(dt=tk-t)�v�Z
		dD = dt / 360;	//���莞���ɑ΂���␳�l
		D = D + dD;
	}
	return D;
}
//=========================================================================
// ���̏o/���̓���v�Z
// �����@ .... �v�Z�Ώےn�_�̌o�x�A�ܓx�@lng lat
// �@�@�@ .... �o�v�t���O�@flag(0)=�o�Aflag(1)=�v�Aflag(2)=�쒆
// �߂�l .... �o�v/�쒆�����i0.xxxx���j
//=========================================================================
function calc_MOON(lng,lat,flag) {
	var ans = new Array();
	var T,rm_moon,bt_moon,theta;
	var W,k,dt;
	var dD = 1;	//�␳�l�����l
	var D = 0.5;	//�����v�Z��������(��)

	while( Math.abs(dD) > converge ) {	// �����v�Z
		T = ( K + D + dT ) / 365.25;	//D�̌o�߃����E�X�N(��)
		rm_moon = lng_MOON(T);	//���̉��o
		bt_moon = lat_MOON(T);	//���̉���
		theta = calc_THETA(lng,T,D);	//�P����
		Kou2Seki(rm_moon,bt_moon,T,ans);

		if (flag != 2) {	//�쒆�̂Ƃ��͌v�Z���Ȃ�
			W = para_MOON(T);	//���̎���
			k = - R - E + W;	//���̏o�v���x
		}
		dt = calc_dt(ans[0],ans[1],theta,k,lat,flag);	//���p��(dt=tk-t)�v�Z
		dD = dt / 347.8;	//���莞���ɑ΂���␳�l
		D = D + dD;
	}
	if (D < 0 || D >= 1) { D = ""; }	//�o�v�Ȃ�
	return D;
}
//=========================================================================
// ����(D)�ɂ����鉩�o�A����(��(T),��(T))�̓V�̂̕��ʊp(A)�v�Z
// �����@ .... �V�̂̉��o�A����(��(T),��(T))(�x)�@ramda,beta
// �@�@�@ .... J2000.0����̌o�߃����E�X�N(x.xxx��)�@T
// �@�@�@ .... ���{���ɂ�邻�̓��O������̌o�ߎ���(0.xxx��)�@D
// �@�@�@ .... �ϑ��n�_�̌o�x�A�ܓx�@lng,lat
// �߂�l .... ���x(xx.x�x)
//=========================================================================
function calc_AZ(ramda,beta,T,D,lng,lat) {
	var ans = new Array();
	Kou2Seki(ramda,beta,T,ans);	//���� -> �ԓ��ϊ�
	return calc_AZe(ans[0],ans[1],T,D,lng,lat) ;
}
//=========================================================================
// ����(D)�ɂ�����Ԍo�A�Ԉ�(��(T),��(T))(�x)�̓V�̂̕��ʊp(A)�v�Z
//=========================================================================
function calc_AZe(alpha,delta,T,D,lng,lat) {
	var theta,t,A,A0,A1;
	theta = calc_THETA(lng,T,D);	//�P����
	t = theta - alpha;	//�V�̂̎��p
	with(Math) {
	A0 =-cos(pai * delta) * sin(pai * t);
	A1 = sin(pai * delta) * cos(pai * lat) - cos(pai * delta) * sin(pai * lat) * cos(pai * t);
	A  = atan(A0 / A1) / pai;
	if (A1 > 0 && A < 0) {  A = 360 + A; }	//���ꂪ�v���X�̂Ƃ���-90<A<90
	if (A1 < 0) {  A += 180; }	//���ꂪ�}�C�i�X�̂Ƃ���90<A<270 -> +180��
	A = ( round(A * 10) ) / 10;
	}
	return A ;
}
//=========================================================================
// ����(D)�ɂ����鉩�o�A����(��(T),��(T))�̓V�̂̍��x(h)�v�Z
// �����@ .... �V�̂̉��o�A����(��(T),��(T))(�x)�@ramda,beta
// �@�@�@ .... J2000.0����̌o�߃����E�X�N(x.xxx��)�@T
// �@�@�@ .... ���{���ɂ�邻�̓��O������̌o�ߎ���(0.xxx��)�@D
// �@�@�@ .... �ϑ��n�_�̌o�x�A�ܓx�@lng,lat
// �߂�l .... ���x(xx.x�x)
//=========================================================================
function calc_EL(ramda,beta,T,D,lng,lat) {
	var ans = new Array();
	Kou2Seki(ramda,beta,T,ans);	//���� -> �ԓ��ϊ�
	return calc_ELe(ans[0],ans[1],T,D,lng,lat);
}
//=========================================================================
// ����(D)�ɂ�����Ԍo�A�Ԉ�(��(T),��(T))(�x)�̓V�̂̍��x(h)�v�Z
//=========================================================================
function calc_ELe(alpha,delta,T,D,lng,lat) {
	var theta,t,h;
	theta = calc_THETA(lng,T,D);	//�P����
	t = theta - alpha;	//�V�̂̎��p
	with(Math) {
	h  = sin(pai * delta) * sin(pai * lat);
	h += cos(pai * delta) * cos(pai * lat) * cos(pai * t);
	h  = asin(h) / pai;

	h += 0.0167 / tan( pai * (h + 8.6/(h + 4.4)) );	// ��C���␳
	h = ( round(h * 10) ) / 10;
	}
	return h ;
}
//=========================================================================
// �o�v�_(k)�̎��p(tk)�ƓV�̂̎��p(t)�Ƃ̍�(dt=tk-t)���v�Z����
// �����@ .... �V�̂̐Ԍo�A�Ԉ�(��(T),��(T))(�x)�@alpha,delta
// �@�@�@ .... �ϑ��n�_�̏o�v���x(�x)�A�P������(�x)�A�ܓx(�x)�@k,theta,lat
// �@�@�@ .... �o�v�t���O�@flag(0)=�o�Aflag(1)=�v�Aflag(2)=�쒆
// �߂�l .... ���p�̍��@dt
//=========================================================================
function calc_dt(alpha,delta,theta,k,lat,flag) {
	var dt,tk,t;
	if (flag == 2) { tk = 0; }	//�쒆�̏ꍇ�͓V�̂̎��p��Ԃ�
	else {
		with(Math) {
		tk  = sin(pai * k) - sin(pai * delta) * sin(pai * lat);
		tk /= cos(pai * delta) * cos(pai * lat);
		tk  = acos(tk) / pai;	//�o�v�_�̎��p
		}
		// tk�͏o�̂Ƃ��}�C�i�X�A�v�̂Ƃ��v���X
		if(flag == 0 && tk > 0) { tk = -tk; }
		if(flag == 1 && tk < 0) { tk = -tk; }
	}
	t = theta - alpha;	//�V�̂̎��p
	dt = tk - t;
	// dt�̐�Βl��180���ȉ��ɒ���
	if (dt >  180) { while ( dt >  180 ) { dt -= 360; } }
	if (dt < -180) { while ( dt < -180 ) { dt += 360; } }
	return dt ;
}
//=========================================================================
// 2000�N1��1���͊w�����߂���̌o�ߓ���(K)�v�Z
// �����@ .... (2000+YY)�NMM��DD��0��(�n����:I=9)�@YY,MM,DD
// �߂�l .... J2000.0(2000�N1��1���͊w������)����̌o�ߓ���(K��)
//=========================================================================
function calc_K(yy,mm,dd) {
	var YY,MM,DD,K;
	YY = yy; MM = mm; DD = dd;
	// 1���A2���̏ꍇ�͑O�N��13���A14���Ƃ݂Ȃ��Čv�Z
	if( MM < 3.0 ){ YY -= 1.0; MM += 12.0; }

	K  = 365*YY + 30*MM + DD - 33.5 - 9/24;	//�n����:I=9
	K += Math.floor( 3*(MM + 1) / 5 );
	K += Math.floor( YY / 4 );
	return K ;
}
//=========================================================================
// �ϑ��n�_�̍P������(�x)�̌v�Z
// �����@ .... �ϑ��n�_�̌o�x(�x)�@lng
// �@�@�@ .... J2000.0����̌o�߃����E�X�N(�͊w��)(x.xxx��)�@T
// �@�@�@ .... ���{���ɂ�邻�̓��O������̌o�ߎ���(�͊w��)(0.xxx��)�@D
// �߂�l .... �ϑ��n�_�̍P������(�x)
//=========================================================================
function calc_THETA(lng,T,D) {
	return norm(325.4606 + 360.007700536*T + 0.00000003879*T*T + 360*D + lng);
}
//=========================================================================
// �������W -> �ԓ����W�ϊ��iT �͗͊w��)
// �����@ .... ���o�A����(��(T),��(T))(�x)�@ramda,beta
// �߂�l .... �Ԍo�A�Ԉ�(��(T),��(T))(�x)�@ans[0],ans[1]
//=========================================================================
function Kou2Seki(ramda,beta,T,ans) {
	var U,V,W;
	var e = (23.439291 - .000130042 * T) * pai;	//�����X�p
	var rm = ramda * pai;
	var bt = beta * pai;
	with(Math) {
	U = cos( bt ) * cos( rm );
	V =-sin( bt ) * sin( e ) + cos( bt ) * sin( rm ) * cos( e );
	W = sin( bt ) * cos( e ) + cos( bt ) * sin( rm ) * sin( e );
	ans[0] = V / U;
	ans[1] = W / sqrt( U * U + V * V );
	ans[0] = atan( ans[0] ) / pai;
	if (U < 0) ans[0] += 180;	//U���}�C�i�X�̂Ƃ���90<��<270 -> +180��
	ans[1] = atan( ans[1] ) / pai;
	}
}
//=========================================================================
// �p�x�̐��K�����s���B���Ȃ킿�����͈̔͂� 0���Ɓ�360 �ɂ���B
//=========================================================================
function norm(angle) {
	return angle - 360 * Math.floor(angle / 360);
}
//=========================================================================
// ���ԁF���l->���ԁF�����ϊ�(xx.xxxx -> hh:mm)
//=========================================================================
function num2jifun(num) {
	var num1,num2;
	num1 = Math.floor( num );	//������(=��)
	num2 = ( num - num1 ) * 60;	//�����_��(=��)
	num2 = Math.round( num2 );
	if ( num2 == 60 ) { num1 += 1; num2 -= 60; }
	if ( num2 < 10 )  { num2 = "0" + num2; }
	return ( num1 + ":" + num2 );
}
//=========================================================================
// �o�ܓx�F�x��->�o�ܓx�F���l�ϊ�(dd:ff -> xx.xxxx)
//=========================================================================
function dofun2num(dofun) {
	var str = new Array();
	str = dofun.split(":");
	str[0] = str[0]*1; str[1] = str[1]*1;	//*1�͕����𐔒l��
	if ( str[0] < 0 ) { str[1] = -str[1]; }	//����(�}�C�i�X)����
	return str[0] + str[1] / 60;
}
//=========================================================================
// ���z�̉��o ��sun(T) ���v�Z����iT �͗͊w���j
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
// ���z�̋��� r(T) ���v�Z����iT �͗͊w���j
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
// ���̉��o ��moon(T) ���v�Z����iT �͗͊w���j
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
// ���̉��� ��moon(T) ���v�Z����iT �͗͊w���j
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
// ���̎��� ��(T) ���v�Z����iT �͗͊w���j
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
// �����o�v�v�Z�̓s�s(�o�ܓx/�W��)�I��
// �����@ .... �Ȃ�
// �߂�l .... �Ȃ�
//=========================================================================
var city = new Array("�N���A","�t��","����","�D�y","����","���H","����","�X","�H�c","����","���","�R�`","����","�F�s�{","�O��","����","�Y�a","����","��t","���l","���}��","�V��","�x�R","����","����","����","�b�{","��","���É�","�É�","�x�m�R","���s","���","��","�ޗ�","���","�_��","�a�̎R","����","���]","���R","�L��","�R��","����","����","���R","���m","����","����","�啪","�F�{","����","�{��","������","�ߔe");
var tokei = new Array("0:0","141:41","142:22","141:21","145:35","144:24","140:45","140:44","140:07","141:09","140:52","140:21","140:28","139:53","139:04","140:29","139:39","139:45","140:07","139:39","142:11","139:02","137:13","138:11","136:39","136:13","138:34","136:46","136:55","138:23","138:44","135:45","135:52","136:31","135:50","135:29","135:11","135:10","134:14","133:03","133:56","132:27","131:28","134:03","134:33","132:46","133:32","130:24","130:18","131:37","130:43","129:52","131:25","130:33","127:40");
var hokui = new Array("0:0","45:25","43:36","43:04","43:20","42:59","41:49","40:49","39:43","39:42","38:16","38:15","37:45","36:34","36:23","36:22","35:51","35:39","35:36","35:27","27:05","37:55","36:41","36:39","36:34","36:04","35:40","35:25","35:10","34:58","35:21","35:01","35:00","34:44","34:41","34:41","34:41","34:14","35:30","35:28","34:40","34:23","34:11","34:21","34:04","33:50","33:33","33:35","33:15","33:14","32:48","32:45","31:54","31:36","26:13");
var hyoko = new Array("0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","3776","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0");

function citySelect(form, sel){
	c_city = sel.options[sel.selectedIndex].value;
	if(c_city == "�N���A") {
		DeleteCookie("�Z�Z�Z_koyomi"); alert('�n��������l�u'+city[idx]+'�v�ɖ߂��܂�');
	} else {
		for (i = 0; i < city.length; i++) {
			if(c_city == city[i]) {
				if(navigator.appName=="Netscape" && navigator.appVersion.substring(0,4)>=4.05){ c_city = escape(city[i]); } else { c_city = city[i]; }
				c_tokei = tokei[i]; c_hokui = hokui[i]; c_hyoko = hyoko[i];
			}
		}
		set_data=c_city+","+c_tokei+","+c_hokui+","+c_hyoko;
		cookie_name="�Z�Z�Z_koyomi";
		setCookieData(set_data);
		alert('�n����u'+unescape(c_city)+'�v�ɕύX���܂��B�u�N���A�v�ŏ����l�ɖ߂�܂��B');
	}
	location.reload();
}
//=========================================================================
// ���邤�N�`�F�b�N
//=========================================================================
function leap(year) {
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
		{ monthDays[1] = 29; } else { monthDays[1] = 28; }
}
