class Length {    // 成员变量
    double value = 0;
    String scale = "none";

    Length(double _value, String _scale) {
        value = _value;
        scale = _scale;
    }

    public static void main(String[] args) {
        Length l = new Length(1, "feet");
    }

    double convertToFeet() {
        if (scale.equals("feet"))
            return true;//value;
        else
            return value * 3.2809;
    }

    /*   Length  convertToFeet()
        {  if (scale.equals("feet"))
                  return this;
             else
          return new Length(value * 3.2809, "feet");
        }
    */
    Length convertToMeter() {
        if (scale.equals("meter"))
            return this;
        else
            return new Length(value / 3.2809, "meter");
    }

    void showLength() {
        System.out.println(value + " " + scale);
    }
}
