
package ma.emsi.sabri;

import ma.emsi.sabri.outil.meteo.MeteoTool;

public class TestMeteo {
    public static void main(String[] args) {

        MeteoTool meteoTool = new MeteoTool();

        boolean pluiePrevue = meteoTool.isPluiePrevue(48.85, 2.35, 0, 3);

        System.out.println("Ville : Paris");
        System.out.println("Latitude : 48.85");
        System.out.println("Longitude : 2.35");
        System.out.println("Période : aujourd'hui et les 2 jours suivants");
        System.out.println("Pluie prévue : " + pluiePrevue);
    }
}
