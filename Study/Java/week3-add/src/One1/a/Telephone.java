import java.util.*;

abstract class Telephone {
    final int local_Call = 1;
    final int distance_Call = 3;
    final int international_Call = 9;
    long phoneNumber;
    double balance;

    abstract boolean charge_Mode(int call_Mode);

    double getBalance() {
        return balance;
    }
}

class Mobile_Phone extends Telephone {
    String networkType;

    String getType() {
        return networkType;
    }

    boolean charge_Mode(int call_Mode) {
        if (balance > 0.6) {
            balance -= 0.6;
            return true;
        } else
            return false;
    }
}

abstract class Fixed_Telephone extends Telephone {
    int monthFee;
}

class Ordinary_phone extends Fixed_Telephone {
    boolean longdistanceService;
    boolean internationalService;

    boolean charge_Mode(int call_Mode) {
        if (call_Mode == distance_Call && !longdistanceService) return false;
        if (call_Mode == international_Call && !internationalService) return false;
//�����ڳ�;�͹��ʳ�;�����Ƿ�ͨ
        if (balance > (0.2 * call_Mode)) {
            balance -= (0.2 * call_Mode);
            return true;
        } else
            return false;
    }
}

class IP_Phone extends Fixed_Telephone {
    boolean started;
    Date expireDate;        //Date��ϵͳ����������һ�����������

    boolean charge_Mode(int call_Mode) {
        if (!started) {
            expireDate.setDate(expireDate.getMonth() + 6);
        }
        //���ð�������
        else
            started = true;
        if (balance > 0.3 && expireDate.after(new Date())) {   //new Calendar ()����һ��������ǰ���ڵ�Date��Ķ���
//after()������Date��ķ������ڵ�ǰ����δ�ﵽʧЧ����ʱ
//expireDate.after(new Calendar ())����true���򷵻�false
            if (call_Mode > local_Call) {
                balance -= (0.1 * call_Mode + 0.2);
//����0.2���л���
                return true;
            } else
                return false;
            //�ٶ�IP�绰ֻ��;
        } else
            return false;
    }
}
