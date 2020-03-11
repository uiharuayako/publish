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
//检查国内长途和国际长途服务是否开通
        if (balance > (0.2 * call_Mode)) {
            balance -= (0.2 * call_Mode);
            return true;
        } else
            return false;
    }
}

class IP_Phone extends Fixed_Telephone {
    boolean started;
    Date expireDate;        //Date是系统类其对象代表一个具体的日期

    boolean charge_Mode(int call_Mode) {
        if (!started) {
            expireDate.setDate(expireDate.getMonth() + 6);
        }
        //设置半年后过期
        else
            started = true;
        if (balance > 0.3 && expireDate.after(new Date())) {   //new Calendar ()创建一个包含当前日期的Date类的对象
//after()方法是Date类的方法，在当前日期未达到失效日期时
//expireDate.after(new Calendar ())返回true否则返回false
            if (call_Mode > local_Call) {
                balance -= (0.1 * call_Mode + 0.2);
//其中0.2是市话费
                return true;
            } else
                return false;
            //假定IP电话只打长途
        } else
            return false;
    }
}
