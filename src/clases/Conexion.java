package clases;


import java.sql.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Conexion {
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    public Conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bdclientes","root","1234");
            System.out.println("Conectado BD");
        } catch (Exception e) {
            System.out.println("Error al conectar BD");
        }
    }
    
    public int Reusuario(String cedula, String nombre, String apellido, String direccion, String genero, String estado){
        int res=0;
        try {
            ps=cn.prepareStatement("insert into datos_clientes (Cedula, Nombre, Apellido,Direccion,Genero,Estado) values (?,?,?,?,?,?);");            
            ps.setString(1, cedula);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, direccion);
            ps.setString(5, genero);
            ps.setString(6, estado);
            res=ps.executeUpdate();
            System.out.println("Usuario registrado correctamente");
        } catch (Exception e) {
            System.out.println("Error al registrar");
        }
        return res;
        
    }
    public int Acusuario(String cedula, String nombre, String apellido, String direccion, String genero, String estado, String id){
        int res=0;
        try {
            ps=cn.prepareStatement("update datos_clientes set Cedula=?,Nombre=?,Apellido=?,Direccion=?,Genero=?,Estado=? where IdCliente=?");
            ps.setString(1, cedula);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, direccion);
            ps.setString(5, genero);
            ps.setString(6, estado);
            ps.setString(7, id);
            res=ps.executeUpdate();
            System.out.println("Datos modificados correctamente");
        } catch (Exception e) {
            System.out.println("Error al modificar datos");
        }
        return res;
    }
    
    
    public int Acusuario2(String cedula, String fecha, String vendedor, String tipo, String articulo, String cantidad, String precio, String id){
        int res=0;
        try {
            ps=cn.prepareStatement("update datos_compras set Cedula=?,Fecha=?,Vendedor=?,Tipo_de_compra=?,Articulo=?,Cantidad=?,Precio=? where IdCliente=?");
            ps.setString(1, cedula);
            ps.setString(2, fecha);
            ps.setString(3, vendedor);
            ps.setString(4, tipo);
            ps.setString(5, articulo);
            ps.setString(6, cantidad);
            ps.setString(7, precio);
            ps.setString(8, id);
            res=ps.executeUpdate();
            System.out.println("Datos modificados correctamente");
        } catch (Exception e) {
            System.out.println("Error al modificar datos");
        }
        return res;
    }
    
    
    public int Elusuario(String id){
        int res=0;
       
        try {
            ps=cn.prepareStatement("delete from datos_clientes where IdCliente = ?;");
            ps.setString(1, id);
            res=ps.executeUpdate();
            System.out.println("Usuario eliminado correctamente");
        } catch (Exception e) {
            System.out.println("Error al eliminar usuario");
        }
         return res;
    }
    
    public int Elusuario2(String id){
        int res=0;
       
        try {
            ps=cn.prepareStatement("delete from datos_compras where IdCliente = ?;");
            ps.setString(1, id);
            res=ps.executeUpdate();
            System.out.println("Compra eliminada correctamente");
        } catch (Exception e) {
            System.out.println("Error al eliminar compra");
        }
         return res;
    }
    
    public ArrayList<Usuarios> ListarUsuarios(){
        ArrayList<Usuarios> res=new ArrayList<>();
        try {
            ps=cn.prepareStatement("select * from datos_clientes ");
            rs=ps.executeQuery();
            while (rs.next()) {                
                Usuarios usuario=new Usuarios();
                usuario.setId(rs.getString("IdCliente"));
                usuario.setCedula(rs.getString("Cedula"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setApellido(rs.getString("Apellido"));
                usuario.setDireccion(rs.getString("Direccion"));
                usuario.setGenero(rs.getString("Genero"));
                usuario.setEstado(rs.getString("Estado"));
                res.add(usuario);
            }
        } catch (Exception e) {
        }
        return res;
    }
    
    public ArrayList<Compras> ListarCompras(){
        ArrayList<Compras> res=new ArrayList<>();
        try {
            ps=cn.prepareStatement("select * from datos_compras ");
            rs=ps.executeQuery();
            while (rs.next()) {                
                Compras compra=new Compras();
                compra.setId(rs.getString("IdCliente"));
                compra.setCedula(rs.getString("Cedula"));
                compra.setFecha(rs.getString("Fecha"));
                compra.setVendedor(rs.getString("Vendedor"));
                compra.setTipo(rs.getString("Tipo_de_compra"));
                compra.setArticulo(rs.getString("Articulo"));
                compra.setCantidad(rs.getString("Cantidad"));
                compra.setPrecio(rs.getString("Precio"));
                res.add(compra);
            }
        } catch (Exception e) {
        }
        return res;
    }
    
    public int Reusuario2(String cedula, String fecha, String vendedor, String tipo, String articulo, String cantidad, String precio){
        int res=0;
        try {
            ps=cn.prepareStatement("insert into datos_compras (Cedula, Fecha, Vendedor,Tipo_de_compra,Articulo,Cantidad,Precio) values (?,?,?,?,?,?,?);");            
            ps.setString(1, cedula);
            ps.setString(2, fecha);
            ps.setString(3, vendedor);
            ps.setString(4, tipo);
            ps.setString(5, articulo);
            ps.setString(6, cantidad);
            ps.setString(7, precio);
            res=ps.executeUpdate();
            System.out.println("Compra registrada correctamente");
        } catch (Exception e) {
            System.out.println("Error al registrar");
        }
        return res;
               
        
    }
    
    public Compras BuscarCompra(String id) {
        Compras compra = null;
        try {
            ps = cn.prepareStatement("select * from datos_compras where IdCliente = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                compra = new Compras();
                compra.setId(rs.getString("IdCliente"));
                compra.setCedula(rs.getString("Cedula"));
                compra.setFecha(rs.getString("Fecha"));
                compra.setVendedor(rs.getString("Vendedor"));
                compra.setTipo(rs.getString("Tipo_de_compra"));
                compra.setArticulo(rs.getString("Articulo"));
                compra.setCantidad(rs.getString("Cantidad"));
                compra.setPrecio(rs.getString("Precio"));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar compra: " + e.getMessage());
        }
        return compra;
    }
    
    public Usuarios BuscarUsuario(String id) {
    Usuarios usuario = null;
    try {
        ps = cn.prepareStatement("SELECT * FROM datos_clientes WHERE IdCliente = ?");
        ps.setString(1, id);
        rs = ps.executeQuery();
        if (rs.next()) {
            usuario = new Usuarios();
            usuario.setId(rs.getString("IdCliente"));
            usuario.setCedula(rs.getString("Cedula"));
            usuario.setNombre(rs.getString("Nombre"));
            usuario.setApellido(rs.getString("Apellido"));
            usuario.setDireccion(rs.getString("Direccion"));
            usuario.setGenero(rs.getString("Genero"));
            usuario.setEstado(rs.getString("Estado"));
        }
    } catch (SQLException e) {
        System.out.println("Error al buscar usuario: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
    return usuario;
}

     public void descargarDatos() {
    try {
        Statement stmt = cn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from datos_clientes a inner join datos_compras b on a.Cedula = b.Cedula order by a.Cedula;");

        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\luisl\\OneDrive\\Desktop\\REPORTES TXT\\datos.txt"));

        String currentCedula = "";
        String currentNombre = "";
        double currentTotal = 0.0;

        //anchuras de columna
        int[] columnWidths = {10, 20, 15, 20, 10, 10, 10};

        while (rs.next()) {
            String cedula = rs.getString("Cedula");
            String nombre = rs.getString("Nombre");

            
            if (!currentCedula.equals(cedula)) {
                if (!currentCedula.equals("")) {
                    
                    writer.write(String.format("%" + (columnWidths[0] + columnWidths[1] + columnWidths[2] + columnWidths[3] + columnWidths[4] + columnWidths[5]) + "s Total: %.2f\n", "", currentTotal));
                   
                    writer.write("\n\n");
                }
                currentCedula = cedula;
                currentNombre = nombre;
                currentTotal = 0.0;
                writer.write(String.format("Cédula: %-10s Nombre: %-20s\n", currentCedula, currentNombre));
                writer.write(String.format("%-10s%-20s%-15s%-20s%-10s%-10s%-10s\n", "Fecha", "Vendedor", "Tipo_de_compra", "Articulo", "Cantidad", "Precio", "Monto"));
            }

         
            writer.write(String.format("%-" + columnWidths[0] + "s", rs.getString("Fecha")));
            writer.write(String.format("%-" + columnWidths[1] + "s", rs.getString("Vendedor")));
            writer.write(String.format("%-" + columnWidths[2] + "s", rs.getString("Tipo_de_compra")));
            writer.write(String.format("%-" + columnWidths[3] + "s", rs.getString("Articulo")));
            writer.write(String.format("%-" + columnWidths[4] + "s", rs.getString("Cantidad")));
            writer.write(String.format("%-" + columnWidths[5] + "s", rs.getString("Precio")));

           
            double precio = Double.parseDouble(rs.getString("Precio"));
            double cantidad = Double.parseDouble(rs.getString("Cantidad"));
            double total = precio * cantidad;
            writer.write(String.format("%-" + columnWidths[6] + ".2f\n", total));
            currentTotal += total;
        }

      
        writer.write(String.format("%" + (columnWidths[0] + columnWidths[1] + columnWidths[2] + columnWidths[3] + columnWidths[4] + columnWidths[5]) + "s Total: %.2f\n", "", currentTotal));

        writer.close();
        JOptionPane.showMessageDialog(null, "Datos guardados en datos.txt");
    } catch (SQLException | IOException ex) {
        ex.printStackTrace();
    }
}



    
}





