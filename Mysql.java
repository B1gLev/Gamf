public void mysqlSetup(){
        host = "ip";
        port = 3306;
        database = "adatbázis";
        username = "felhasználó";
        password = "jelszó";

        final Properties prop = new Properties();
        prop.setProperty("user",username);
        prop.setProperty("password",password);
        prop.setProperty("useSSL","false");
        prop.setProperty("autoReconnect","true");

        try {
            synchronized (this) {
                if (getConnection() != null && !getConnection().isClosed()) {
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                setConnection(
                        DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, prop));

                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Sikeres csatlakozás! MYSQL");
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
