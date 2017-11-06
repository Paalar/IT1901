# it1901

Concert Management
========

## Hva er det?

Concert Management er et system for å lett organisere festivaler. Concert Management gjør det lett å arrangere konserter på flere scener, lage og finne spesifikasjoner for hver artist som skal framføre, teknisk gjennomføring av konsertene og økonomisk informasjon angående konsertene.

## Installasjon

### Forhåndskrav
Må ha Java 8 installert
* [Link til Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html)


### Anbefalinger
Hvis man vil endre på noe selv, anbefales det å installere Scene Builder som er en applikasjon som forenkler å lage det grafiske til Java FX applikasjoner.
* [Link til Scene Builder](http://gluonhq.com/products/scene-builder/)


---


For å komme i gang med din egen festival trenger du bare å laste ned dette open-source prosjektet:

(Akkurat nå er prosjektet lukket på GitHub.)

    git clone https://github.com/Paalar/it1901.git
    cd ConcertManagement\output\artifacts\ConcertManagement
    
 ##### Windows & Mac
 Kjør ConcertManagement.jar
 ##### Linux
 Kjør runLinux.sh
	

## Bruk

Når du har åpnet programmet er det bare å trykke på en knapp for å logge på som den rollen. 
#### Arrangør:
Som arrangør kan du endre festival og scene for å endre oversikten og så kan du trykke på en artist for å få opp de lyd og lys-teknikerne som skal jobbe på den konserten.
___
#### Bookingansvarlig
I tab 1: “Info om band” kan du trykke på et band for å se alle tekniske behov, nøkkelinformasjon og tidligere konserter. Det er også her mulig å filtrere ut band som er her i år og band som ikke skal være her i år.
I tab 2: “Lag tilbud” kan du sende tilbud til manager for et band for booking på en spesifikk dato og pris.
I tab 3 “Tidligere konserter” kan du se alle konserter som har vært på uka tidligere, men ikke i år. Du kan velge å bare vise konserter for en spesifikk sjanger.
___

#### Bookingsjef
I tab 1: “Forslag til billettpris” kan du søke på en band som har vært på Uka tidligere men som ikke er i Uka 2017 og få forslag til billettpriser på de 3 forskjellige scenene. 
I tab 2: “Rapport” kan du velge en scene for å få oversikt over konserter som har vært på Uka før. Der kan du se ting som artist, sjanger, solgte billetter, billettpris, artistpris og hva det økonomiske resultatet av konserten ble.
I tab 3: “Tilbud” kan du se alle tilbud som har blitt sendt av Bookingansvarlig. Her kan også godkjenne eller avslå tilbud.
I tab 4: “Konsertdatoer” så kan du se antall ledige datoer på alle scenene. Og en liste over når det er ledig.
___

#### Manager
I manager kan du velge en artist som skal spille i år og legge til og fjerne behov. Og helt til slutt kan du trykke “Send behov”.
____

#### Tekniker 
I tekniker kan du velge en av teknikerne i listen, eller søke for å få en oversikt over hvor og når den personen skal jobbe. 
____

#### PR-Ansvarlig
Som PR-Ansvarlig kan du søke/ velge et band for å få opp kontaktinformasjon, salgstall, lenker til presseomtaler og en presseomtale.
____


## Kode- og andre standarder
* Alle filnavn, funksjoner og variabelnavn er skrevet i camel case.
* Kommentarer er skrevet på norsk.
* Filer, funksjoner og variabelnavn er skrevet på englesk, med JSON variabler som unntak.
* Mappe groupFive skal bare bli brukt til fxml og klassene som kjører hovedprogrammet.
* Mappe JSON skal bli brukt til JSON klassene, lesing og skriving til JSON filene.
* Mappe resources skal bli brukt til å oppbevare eksterne filer som forbedrer programmet (JSON filene, CSS og eksterne bibliotek.
* Mappe util blir brukt til å lagre tungvinne funksjoner eller funksjoner som blir ofte brukt (som å gå gjennom JSON)
* Mappe output blir brukt til å lagre den nyeste versjonen av produktet.
  * I IntelliJ. Build > Build Artefacts... > ConcertManagement > Build 


## Bidragsytere
I alfabetisk rekkefølge:
* Astrid Vik
* Petter Rein
* Pål Larsen
* Sondre Dahl
* Svenn Grønbeck
* Tinus Flagstad


## Gruppeprosjekt 
#### Description
Tenk deg at du skal lage et system for en gjeng i en studentfestival som har ansvar for å arrangere konserter på flere scener, som involverer rigging av scene, teknisk gjennomføring av konserter, samt booking av band. Brukerhistoriene er oppgitt i alfabetisk rekkefølge.

### Bugs
* På arrangør, når man velger en festival eller scene forandrer ikke teknikerene seg.'
* Kan endre teksten til scenene, dette burde ikke skje.
* Funnet bug når man ikke trykker på en bruker på startside
* Hvis ikke en konsert er trykket på så stopper programmet.
* ScrollPane bakgrunn ikke faktisk ble fargen som ønsket
* Når man maksimerer vinduet på hovedsiden blir det ikke sentrert


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
