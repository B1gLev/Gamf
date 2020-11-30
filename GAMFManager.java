import java.sql.*;
import java.util.ArrayList;

public class GAMFManager {
    /*
     * 3. Hozzon létre egy GAMFManager nevű osztályt, minden adatbázissal kapcsolatos művelet ide kerüljön.
     *
     * */
    public static void main(String[] args){
        connect();
        listaz();
        nagyKreditszamuak();
        //specializacioBeszurasa();
        specializaciokTantargyai();
    }

    private static Connection c;
    private static void connect(){
        try{
            Class.forName("org.sqlite.JDBC");
            c= DriverManager.getConnection("jdbc:sqlite:InfoTantargyak.db");
            System.out.println("Sikeres SQLITE csatlakozás!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /*
     * 4. Listázza ki az összes tantárgyat név szerint ABC sorrendben. (függvény neve listaz legyen)
     *
     * */
    public static void listaz(){
        ArrayList<String> nevek = new ArrayList<>();
        String nev;
        String sql = "SELECT * FROM Tantargyak ORDER BY Nev ASC;";
        try{
             PreparedStatement statement = c.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();
             while (resultSet.next()){
                 nev = resultSet.getString("Nev");
                 nevek.add(nev);
             }
             System.out.println("----------------------------");
             System.out.println("4. listaz");
             System.out.println(" ");
             for (String str:nevek){
                 System.out.println(str);
             }
             System.out.println(" ");
             System.out.println("----------------------------");
             System.out.println("5. nagyKreditszamuak");
             System.out.println(" ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * 5. Kérdezze le az 5 kreditnél nagyobb kreditszámú tantárgyakat. (függvény neve: nagyKreditszamuak)
     *
     * */
    public static void nagyKreditszamuak(){
        ArrayList<String> nagyKreditszam = new ArrayList<>();
        String nev;
        String sql = "SELECT * FROM Tantargyak GROUP BY Id HAVING Kredit > 5;";
        try{
            PreparedStatement statement = c.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                nev = resultSet.getString("Nev");
                nagyKreditszam.add(nev);
            }

            for (String str:nagyKreditszam){
                System.out.println(str);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    * 6. Kérdezze le a félévenkénti kreditek számát (csoportosítás félévenként). (függvény neve: felevKreditek)
    *
    * */
    public static void felevKreditek(){
    }

    /*
    * 7. Készítsen egy függvényt, amellyel egy új specializációt lehet felvinni az adatbázisba.
    *    (függvény neve: specializacioBeszurasa)
    *
    * */
    public static void specializacioBeszurasa(){
        String sql = "INSERT INTO Specializaciok(Nev,Megjegyzes) VALUES(?,?);";
        try{
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1, "Specializaciok név új");
            statement.setString(2, "Specializaciok megjegyzés új");
            statement.executeUpdate();
            System.out.println("Sikeres beszúrás!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * 8. Listázza ki az összes tantárgyat úgy, hogy a specializáció nevét is tartalmazza és rendezze specializáció neve szerint ABC sorrendbe.
     *    Hozzon létre egy osztályt, amely reprezentálja a lekérdezés eredményét. (függvény neve: specializaciokTantargyai)
     *
     * */
    public static void specializaciokTantargyai(){
        ArrayList<String> nevek = new ArrayList<>();
        String nev;
        String sql = "SELECT nev FROM Tantargyak UNION SELECT nev FROM Specializaciok ORDER BY Nev ASC;";
        try{
            PreparedStatement statement = c.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                nev = resultSet.getString("Nev");
                nevek.add(nev);
            }
            System.out.println(" ");
            System.out.println("----------------------------");
            System.out.println("8. specializaciokTantargyai");
            System.out.println(" ");
            for (String str:nevek){
                System.out.println(str);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
