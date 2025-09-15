package ee.taltech.algoritmid.guessinggame;

import java.util.Arrays;
import java.util.Comparator;

public class GuessingGame {

    // privaatne massiiv gameOne jaoks, et sorteerida ainult üks kord
    // muidu ma tegi mitu korda ja see ei olnud optimaalne
    private Product[] sortedProductsGameOne = null;

    public GuessingGame() {
        // don't remove
        // sisaldab molemaid mange
    }

    /**
     * @param bot - bot which you can ask for the feedback about the price you think should be correct
     * @param productArray - Array from which the bot selected the winning product (NB! is always the SAME ARRAY).
     * @return the name of the correct product.
     */
    public String gameOne(Bot bot, Product[] productArray) {
        // sorteeri massiiv ainult esimest korda
        // aka kui see on null
        if (sortedProductsGameOne == null) {
            sortedProductsGameOne = Arrays.copyOf(productArray, productArray.length);
            // teeb koopia arrayst et kogemata orginaal ei muudaks :)
            // see rida also muudab sortedProductsGameOne mitte nulliks
            Arrays.sort(sortedProductsGameOne, Comparator.comparingDouble(Product::getPrice));
            // sorteerime array hinna jargi, kuna binaar otsing tootab ainult sorteeritud arraydega
        }

        int left = 0;
        int right = sortedProductsGameOne.length - 1;
        // maarab otsitava vahemiku indexsitd kuna algab nullist ss left on 0
        // teine on siis array pikkus mis ei alga nullist seega korrektse pikkuse jaoks peab tegema -1

        while (left <= right) {
            // kuni on midagi kontrollida veel kui right vaiksem on jarelikult tuhi
            int mid = (left + right) / 2;
            // leiab keskkoha mida kusib boti kaest, optimaalne kuna siis saab kas suurema voi vaiksema
            // vastusel tapselt pool listi
            Product guessProduct = sortedProductsGameOne[mid];
            // paku see product
            String response = bot.isIt(guessProduct.getPrice());
            // kusi boti kaest kas see on oige?

            if (response.equals("You have won!")) {
                return guessProduct.getName();
                // anna tagasi oige producti nimi variable guess product kuna see oli see mid punkt mis enne leidis
            } else if (response.equals("higher")) {
                left = mid + 1;
                // kui on suurem ss jarelikult peab vaiksema poole arrayst mitte arvestama
                // aka liiguta vasakut inexit nii et 0-mid ei lahe arvesse. mid + 1 sest me juba teame et mid pole oige
            } else {
                right = mid - 1;
                // sama aga vastupidi
            }
        }

        return "";
    }


    /**
     * @param bot - bot which you can ask for the feedback about the price you think should be correct
     * @param productArray - Array from which the bot selected the winning product (NB! can be a DIFFERENT ARRAY every time)
     * @return the name of the correct product
     */
    public String gameTwo(Bot bot, Product[] productArray) {
        for (Product product : productArray) {
            // kaib labi koik array elemendid
            // me ei pea siin koopiat tegema kuna ei sorteeri array nii et pole vaja
            // muretseda et orginaali ara rikuks
            String response = bot.isIt(product.getPrice());
            // votame praegu vaadatava toote hinna mille annab botile
            if (response.equals("You have won!")) {
                // kui hind klapib siis voitis
                return product.getName();
                // returni nimi
            }
            // muudel juhtudel ara tee midagi ja for loop jatkub
        }
        return "";
    }
}
