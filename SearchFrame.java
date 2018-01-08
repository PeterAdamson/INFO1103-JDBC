//Author Peter Adamson

	private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/ORCL";
        String userid = "PVFC";
        String password = "PVFC";
         
        Connection conn;
        OracleDataSource ds;
        CallableStatement cstmt;
        String query;
        int productid;
        int price;

        try {
            ds = new OracleDataSource();
            ds.setURL(jdbcUrl);
            conn = ds.getConnection(userid,password);
            
            query = "{ Call Search_Product(?,?,?,?) }";
            cstmt = conn.prepareCall(query);
            
            productid = Integer.parseInt(txtProductID.getText());
            cstmt.setInt(1, productid);    
            cstmt.registerOutParameter(2, Types.VARCHAR);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, Types.NUMERIC);       
            cstmt.executeQuery(); 
            
            lblStatus.setText("Search Successful");
            txtDescription.setText(cstmt.getString(2));
            price = cstmt.getInt(4);
            txtPrice.setText(Integer.toString(price));
            lblStatus.setText("The product price is " + price);
            
            
        } catch (SQLException e) {
            txtDescription.setText("");;
            txtPrice.setText("");
            if (e.getMessage().contains("ORA-01403"))
                lblStatus.setText("Invalid ID");
            else
                lblStatus.setText("Error: " + e.getMessage());
            
        }       
    }//GEN-LAST:event_btnSearchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/ORCL";
        String userid = "PVFC";
        String password = "PVFC";
         
        Connection conn;
        OracleDataSource ds;
        PreparedStatement cstmt;
        String query;
        double val;
        int productid1;
        
        try{
            ds = new OracleDataSource();
            ds.setURL(jdbcUrl);
            conn = ds.getConnection(userid,password);
            
            productid1 = Integer.parseInt(txtProductID.getText());
            val = Double.parseDouble(jTextField1.getText());
            query = "UPDATE PRODUCT_T SET PRODUCTSTANDARDPRICE = ? WHERE PRODUCTID = ?";
            cstmt = conn.prepareStatement(query);
            cstmt.setInt(1,productid1);
            cstmt.setDouble(2,val);
            int rowsAffected = cstmt.executeUpdate();
            txtPrice.setText(jTextField1.getText());
            lblStatus.setText("The product price is changed to " + txtPrice.getText());
        
        } catch (Exception e) {
           lblStatus.setText("Exception encountered:" + e);
            
        }    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/ORCL";
        String userid = "PVFC";
        String password = "PVFC";
         
        Connection conn;
        OracleDataSource ds;
        CallableStatement cstmt;
        String query;
        double val;
        int productid;
        
        try {
            ds = new OracleDataSource();
            ds.setURL(jdbcUrl);
            conn = ds.getConnection(userid,password);
            
            productid = Integer.parseInt(txtProductID.getText());
            val = Double.parseDouble(jTextField1.getText());
            query = ("{call UpdatePrice(?,?)}");
            cstmt = conn.prepareCall(query);
            cstmt.setInt(1,productid);
            cstmt.setDouble(2,val);
            cstmt.registerOutParameter(2, Types.NUMERIC);
            cstmt.execute();
            txtPrice.setText(String.valueOf(val));
            lblStatus.setText("The product price is changed to " + txtPrice.getText());

            
            
        } catch (SQLException e) {
            txtDescription.setText("");;
            txtPrice.setText("");
            if (e.getMessage().contains("ORA-01403"))
                lblStatus.setText("Invalid ID");
            else
                lblStatus.setText("Error: " + e.getMessage());
            
        }   
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
