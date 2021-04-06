# grafikaLab2
Gra nazywa się "World's hardest game", jest to inspiracja jedną z pierwszych gier w jakie miałem okazję zagrać gdy miałem kilka lat. Gra została napisana od zera
przeze mnie, z minimalną pomocą StackOverflow przy niektórych funkcjach matematycznych. Gra jest dosyć trudna do ukończenia dla osób nie mających dobrej koordynacji wzrokowo-ruchowej, lecz poniżej opisałem zmiany jakich dokonać w kodzie gdyby okazała się zbyt trudna.
## Zasady gry
Gra polega na przedostaniu się z jednego zielonego obszaru na ktorym sie spawnujemy,do drugiego który jest zazwyczaj po drugiej stronie mapy. Jeżeli na danym poziomie występują 
żółte monety, grający musi zebrać je wszystkie żeby poziom został zaliczony. Gracz musi unikać wszystkich niebieskich obiektów, ponieważ po zetknięciu z nimi dopisywana jest
"śmierć" do licznika, oraz gracz jest teleportowany na początek poziomu a sam poziom się resetuje. 
## Instrukcja i mechanika poruszania się
Gracz porusza się strzałkami, ściany się "kleją" co ma dodatkowo utrudnić rozgrywkę i zniechęcić gracza do nieprzemyślanego parcia na przód. Ma to szczególne znaczenie w przypadku 
ciasnych przejść.
## Gdyby gra sprawiała za dużo trudności do przejścia i ocenienia
* **Poziom pierwszy i czwarty** W klasie WorldsHardestGame w metodzie loadlvl1() i loadlvl(4) zmienić speed (przedostatni atrybut) BlueBalli na mniejszy.
* **Poziom drugi** W actionPerformed wykomentować linie od 486 do 492 - if(lvl2Line.intersects(new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight()))) {...}
* **Poziom trzeci** W actionPerformed wykomentowac linie od 517 do 523 - if(lvl3Rectangle.intersects(new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight()))) {...}
## Gra składa się z następujących klas:
* Main
* Player
* BlueBall
* Coin
* WorldsHardestGame

# Klasa Main
Zainicjalizowanie gry i sciężki dzwiękowej:

# Klasa Player:
Kilka pól opisujących wymiary i pozycję gracza, i metody odpowiedzialne za poruszanie się:

# Klasa Blueball
Bardzo podobna klasa do klasy Player, rozszerzona o pola speed oraz direction


# Klasa Coin
Klasa opisująca monety które gracz musi zbierac w trakcie rozgrywki

# Klasa WorldsHardestGame
* Klasa główna zawierająca całą mechanikę rozgrywki. Postaram się zrozumiale streścić najważniejsze metody:


### Metoda odpowiedzialna za pasek który w trakcie rozgrywki wyświetla aktualną ilość śmierci oraz czas:
```
   public void printDeathsAndTime(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,700,1200,100);
        g.setColor(Color.white);
        g.setFont(new Font("Comic Sans MS",Font.BOLD,50));
        g.drawString("Deaths: "+deaths,20,750);
        g.drawString("Time: "+(int)timePassed,930,750);
    }
```
### Metody loadlvl1, loadlvl2, loadlvl3 i loadlvl4
Są to metody w których ustawiam parametry takie jak:
- Granice mapy
- Współrzędne gracza
- Listę przeszkód i monet

### RenderPlayer
Jako argument przyjmuję obiekt klasy Graphics na którym rysowana jest gra. Odpowiada za aktualizowanie pozycji gracza i rysowanie jej na bieżąco.

### RenderBorders
Metoda ta sprawdza czy występuje kolizja z granicami mapy, jeżeli taka kolizja wystąpi - otrzymuję informacje z której strony jest to kolizja i w metodzie KeyPressed blokuje ruch 
w tą stronę

## Paint
Tutaj odbywa się rysowanie poszczególnych poziomów gry. Poziom 0 to ekran startowy, a poziom -1 to ekran końcowy. Opiszę renderowanie poziomów na przykładzie poziomu pierwszego.
</br>

**Przed przejściem do konkretnego poziomu konwertuję listy zawierające koordynaty mapy na tablicę, które bedą wykorzystywane do tworzenia Polygon-ów oraz PolyLine.**

```
  int[] mapx = mapCoordinatesX.stream().mapToInt(i->i).toArray();
  int[] mapy = mapCoordinatesY.stream().mapToInt(i->i).toArray();
```

**Następnie ustawiam kolor tła, oraz kolor wypełnienia mapy. Wypełnienie mapy jest dopasowane do kształtu dzięki wyżej opisanej konwersji list na tablice.**

```
             g2d.setColor(new Color(77,77,77));g2d.setColor(new Color(102, 179, 255));
            g2d.fillRect(0,0,1200,800);

            //wypelnienie mapy
            Polygon map = new Polygon(mapx,mapy,mapx.length);
            g2d.setColor(new Color(51,51,51));
            g2d.fillPolygon(map);
```
**Tworzę i rysuję dwa Rectangle - spawn i metę. Sprawdzam czy nie wystąpiła kolizja z Rectanglem meta co oznaczałoby ukończenie poziomu**
```
            g2d.setColor(new Color(0,153,51));
            Rectangle spawn = new Rectangle(200,200,120,220);
            g2d.fillRect(spawn.x,spawn.y,spawn.width,spawn.height);
            Rectangle meta = new Rectangle(860,240,120,220);
            g2d.fillRect(meta.x,meta.y,meta.width,meta.height);
            if(new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight()).intersects(meta)){
                loadlvl2();
                this.level=2;
                System.out.println("Meta!");
            }
```

**Następnie renderuję gracza oraz granicę mapy.**
```
            //player
            this.renderPlayer(g);
            //map borders
            g2d.setColor(Color.black);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawPolyline(mapx,mapy,mapx.length);
```

**Rysuję przeszkody (BlueBall) pobierając je kolejno z listy przesyłając ich współrzędne do metody fillOval**
```
          //Blue balls
            g2d.setColor(new Color(0,102,204));
            for(BlueBall blueBall: blueBallArrayList){
                g2d.fillOval(blueBall.getX(),blueBall.getY(),blueBall.getWidth(),blueBall.getHeight());
            }

            g2d.setColor(new Color(0,26,128));
            for (BlueBall blueBall: blueBallArrayList){
                g2d.drawOval(blueBall.getX(),blueBall.getY(),blueBall.getWidth(),blueBall.getHeight());
            }
```

**Kontroluję ruch (zainicjalizowany w późniejszej metodzie), kolizję i odbijanie się niebieskich kulek od granic mapy. Przemieszczam się kolejno po tablicy koordynatów mapy, generując bieżącą granicę (tempBorder) i bieżącą pozycję
gracza (tempPlayer). Następnie przemieszczam się po liście BlueBalli, generując bieżącą kulke (tempBall). Sprawdzam czy występuje kolizja z tempBorder, jeśli tak wywołuję metodę
"reverseDirection" która odwraca kierunek kulki. Poprzedzam to manualnym odbiciem kulki o 15 jednostek w stronę przeciwną do której się poruszała dzięki czemu zapobiegam 
zaklinowaniu się przeszkody w granicy mapy. Sprawdzam również czy występuje kolizja kulki z graczem, jeśli tak - poziom jest resetowany a graczowi doliczana skucha**

```
          //blue balls movement
            for(int i=0; i<mapCoordinatesX.size()-1;i++){
                Shape tempBorder = new Line2D.Double(mapCoordinatesX.get(i), mapCoordinatesY.get(i), mapCoordinatesX.get(i+1), mapCoordinatesY.get(i+1));
                Shape tempPlayer = new Rectangle2D.Double(player.getX(),player.getY(),player.getWidth(),player.getHeight());
                for(BlueBall blueBall: blueBallArrayList){
                    Shape tempBall = new Ellipse2D.Double(blueBall.getX(),blueBall.getY(),blueBall.getWidth(),blueBall.getHeight());
                    if(tempBorder.intersects(tempBall.getBounds())){
                        blueBall.setX(blueBall.getX()+((-blueBall.getDirection())*15));
                        blueBall.reverseDirection();
                    }
                    if(tempPlayer.intersects(tempBall.getBounds())){
                        player.setX(230); player.setY(300);
                        deaths++;
                    }
                }
            }
```

**Renderowanie granic oraz wypisywanie czasu i ilości śmierci**
```
           this.renderBorders();
            this.printDeathsAndTime(g);
```

## Metoda actionPerformed
**Zapewnia ruch wszystkich przeszkód wykorzystując metody moveRight(), moveDown() itp. Sprawdza również czy monety zostały zebrane, jeżeli moneta.intersects(player) to 
pole collected monety jest ustawiane na true a moneta przestaje być wyświetlana na mapie do momentu skuchy**
* **Poruszająca się linia w poziomie drugim** -
Niewątpliwie element który sprawił najwięcej problemów, wykorzystuje tutaj jedyną funkcję zaczerpniętą z internetu, mianowicie:

```
static Point rotateLineClockWise(Point center, Point edge, int angle) {
        double xRot = (int) center.x + Math.cos(Math.toRadians(angle)) * (edge.x - center.x) - Math.sin(Math.toRadians(angle)) * (edge.y - center.y);
        double yRot = (int) center.y + Math.sin(Math.toRadians(angle)) * (edge.x - center.x) + Math.cos(Math.toRadians(angle)) * (edge.y - center.y);
        return new Point((int) xRot, (int) yRot);
    }
```
Dzięki tej funkcji uzyskuje współrzędne punktu końcowego linni na podstawie kąta jej obrotu, dzięki czemu mogę fizycznie nadpisywać pozycję linii (a nie tylko jej rysunek)
a następnie sprawdzać jej kolizje z graczem.

## Metoda keyPressed
Standardowe poruszanie się wzbogacone o kontrolę kolizji z granicami mapy. Instrukcję if gwarantują mi, że gracz nie wyjdzie poza granicę mapy, ponieważ na przykładzie: gdy 
wystąpi kolizja z prawej strony gracza, jedyny kierunek w który może się poruszyć to kierunek przeciwny do kolizji, czyli w tym wypadku w lewo. Ten system zapewnia również
"klejenie się" gracza do mapy, co dodatkowo utrudnia i urozmaica rozgrywkę.
