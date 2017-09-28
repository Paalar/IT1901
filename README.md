# it1901
## Gruppeprosjekt 
#### Description
Tenk deg at du skal lage et system for en gjeng i en studentfestival som har ansvar for å arrangere konserter på flere scener, som involverer rigging av scene, teknisk gjennomføring av konserter, samt booking av band. Brukerhistoriene er oppgitt i alfabetisk rekkefølge.

### Bugs
#### Known bugs
* Kan endre teksten til scenene, dette burde ikke skje.
* Funnet bug når man ikke trykker på en bruker på startside
* Hvis ikke en konsert er trykket på så stopper programmet.
#### Fixed bugs


### Bruk av JSON
* Ikke lastet opp til master enda.
* All informasjonen blir lagret i en liste. Akkurat nå heter den festivals og starter i main.
* Anbefaler å se på filene i resources mappen.
* Hver variabel og liste har en egen getter.
* I hoved listen, festivals, er det en liste av festival objekter.
  * For å få tak i første festival (UKA 2017), skriv festivals.get(0).
  * Det er 3 string verdier; festival (navn på festivalen), datoStart og datoSlutt. For å få tak i de, bruk getNavnPåVariabel.
  * Det er en liste med Scene objekter. Bruk getScene().get(0) for å få tak i første scene, Dødens dal.
  * Hver scene har et navn, antall plasser og en liste med konsert objekter.
  * For å få tak i første konsert skriv getKonsert().get(0)
  * Sånn fortsetter det gjennom alle nestede listene.
Eksempel på å printe ut alle scene navn og konserter som blir spillt på den scenen under UKA 2017

List<Festival> festivals = JsonDecode.parseJSON();
        for (int i = 0; i < festivals.get(0).getScene().size(); i++) {
            System.out.println(festivals.get(0).getScene().get(i).getNavn());
            for (int n = 0; n < festivals.get(0).getScene().get(i).getKonsert().size(); n++) {
                System.out.println(festivals.get(0).getScene().get(i).getKonsert().get(n).getArtist());
            }
        }
 
Dette vil da printe ut:
Dødens dal
Lorde
Martin Garrix
Storsalen
Astrid S
Lorde4
Knaus
Lorde
Martin Garrix

Det ser litt krunglete ut, men er ikke vanskelig å forstå hva som faktisk skjer, så lenge du husker skrive get(et tall) etter å kallt en get på en liste, som f.eks. getScene().get(1). 


### Kodestandarder
#### Variabler & Kommentarer
* Engelske variabelnavn
* Camelcase
  * Eksempel: public void thisIsAFunction(String aString)
* Kommentarer er på norsk

### Changelog
#### Sprint 1
Brukerhistorier 1,2 ikke 13. er Lagt til, grunnmuren for prosjektet er laget. Mangler et ordentlig design, noen bugs er der. Lite brukervennlig. 
#### Sprint 2
#### Sprint 3
#### Sprint 4
