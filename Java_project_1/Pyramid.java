public class Pyramid 
{
    String pharaoh;
    String modern_name;
    String site;
    String height;
    
    public Pyramid(String pharaoh,String modern_name,String site,String height) 
    {
        this.height = height;
        this.modern_name = modern_name;
        this.pharaoh = pharaoh;
        this.site = site;
    }

    public String getPharaoh() {
        return pharaoh;
    }

    public String getModern_name() {
        return modern_name;
    }

    public String getSite() {
        return site;
    }

    public String getHeight() {
        return height;
    }

    public void setPharaoh(String pharaoh) {
        this.pharaoh = pharaoh;
    }

    public void setModern_name(String modern_name) {
        this.modern_name = modern_name;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Pyramids{" + "pharaoh=" + pharaoh + ", modern_name=" + modern_name + ", site=" + site + ", height=" + height + '}';
    }
    
    
}
