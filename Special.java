public class Special extends Card
{
    private boolean isTax;
    private boolean isDoubleLunch;
    private boolean isDetention;
    private boolean isGo;

    private int tax;

    public Special(String name, int loc, boolean isTax, boolean isDoubleLunch, boolean isDetention, boolean isGo)
    {
        super(name, loc, false, true, false, false);
        
        this.isTax = isTax;
        this.isDoubleLunch = isDoubleLunch;
        this.isDetention = isDetention;
        this.isGo = isGo;

        if(isTax && name.indexOf("Small Tax") != -1)
        {
            tax = 15;
        }
        else if(isTax)
        {
            tax = 25;
        }
    }

    public boolean isTax() {
        return isTax;
    }

    public void setTax(boolean isTax) {
        this.isTax = isTax;
    }

    public boolean isDoubleLunch() {
        return isDoubleLunch;
    }

    public void setDoubleLunch(boolean isDoubleLunch) {
        this.isDoubleLunch = isDoubleLunch;
    }

    public boolean isDetention() {
        return isDetention;
    }

    public void setDetention(boolean isDetention) {
        this.isDetention = isDetention;
    }

    public boolean isGo() {
        return isGo;
    }

    public void setGo(boolean isGo) {
        this.isGo = isGo;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public String toString()
    {
        if(isTax)
        {
            return super.getName() + " [Tax = " + tax + "]";
        }
        else
        {
            return super.getName();
        }
    }
    
}
