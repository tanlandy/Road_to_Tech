package Project_Poly;

class Language {
    protected String name;
    protected Integer numSpeakers;
    protected String regionsSpoken;
    protected String wordOrder;
  
  // give Language a constructor that sets each field to the values passed in.
    Language(String name, Integer numSpeakers, String regionsSpoken, String wordOrder) {
      this.name = name;
      this.numSpeakers = numSpeakers;
      this.regionsSpoken = regionsSpoken;
      this.wordOrder = wordOrder;
    }
  
  // Create a public method for Language called getInfo(). We’ll use this method to display all of its information (using its field values).
    public void getInfo() {
      System.out.println(name + " is spoken by " + numSpeakers + " people mainly in " + regionsSpoken + ". The language follows the word order: " + wordOrder);
    }
  

  public static void main(String[] args) {
    Language hollulu = new Language("hollulu", 1231234, "Antarctic", "Traver");
    hollulu.getInfo();
  
  }
}