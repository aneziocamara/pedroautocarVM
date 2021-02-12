/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.work_space.telas;

import java.sql.*;
import br.com.work_space.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author anezi
 */
public class TelaManPrev extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaManPrev
     */
    public TelaManPrev() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //método para cadastrar manutenção preventiva
    private void cadastrar_manpre() {
        String sql = "insert into tbmanpre(placa, nome, correia_dentada, correia_alternador, correia_arcond, correia_bomba_dagua, filtro_ar, filtro_comb, troca_oleo, velas, cabo_velas, bico_injetor, idcli) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPlaca.getText());
            pst.setString(2, txtNome.getText());
            pst.setString(3, txtCorDent.getText());
            pst.setString(4, txtCorAlt.getText());
            pst.setString(5, txtCorArCond.getText());
            pst.setString(6, txtCorBombAgua.getText());
            pst.setString(7, txtFiltroAr.getText());
            pst.setString(8, txtFiltroComb.getText());
            pst.setString(9, txtTrocOleo.getText());
            pst.setString(10, txtVelas.getText());
            pst.setString(11, txtCaboVelas.getText());
            pst.setString(12, txtLimpBicos.getText());
            pst.setString(13, txtCodCli.getText());
            
            if ((txtNome.getText().isEmpty()) || (txtPlaca.getText().isEmpty()) || (txtCodCli.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados cadastrados com sucesso.");

                    txtNome.setText(null);
                    txtPlaca.setText(null);
                    txtCorDent.setText(null);
                    txtCorAlt.setText(null);
                    txtCorArCond.setText(null);
                    txtCorBombAgua.setText(null);
                    txtFiltroAr.setText(null);
                    txtFiltroComb.setText(null);
                    txtTrocOleo.setText(null);
                    txtVelas.setText(null);
                    txtCaboVelas.setText(null);
                    txtLimpBicos.setText(null);
                    txtCodCli.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //método para pesquisar dados
    private void pesquisar_id() {
        // a linha abaixo cria uma caixa de entrada do tipo JOptionPane
        String num_id = JOptionPane.showInputDialog("Digite o código do cliente.");
        String sql = "select * from tbmanpre where idcli = " + num_id;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtCodCli.setText(rs.getString(1));
                txtData.setText(rs.getString(2));
                txtPlaca.setText(rs.getString(3));
                txtNome.setText(rs.getString(4));
                txtCorDent.setText(rs.getString(5));
                txtCorAlt.setText(rs.getString(6));
                txtCorArCond.setText(rs.getString(7));
                txtCorBombAgua.setText(rs.getString(8));
                txtFiltroAr.setText(rs.getString(9));
                txtFiltroComb.setText(rs.getString(10));
                txtTrocOleo.setText(rs.getString(11));
                txtVelas.setText(rs.getString(12));
                txtCaboVelas.setText(rs.getString(13));
                txtLimpBicos.setText(rs.getString(14));               
                btnManPreAdicionar.setEnabled(false);

            } else {
                JOptionPane.showMessageDialog(null, "Código do cliente não cadastrado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void alterar_manpre() {
        String sql = "update tbmanpre set placa = ?, nome = ?, correia_dentada = ?, correia_alternador = ?, correia_arcond = ?, correia_bomba_dagua = ?, filtro_ar = ?, filtro_comb = ?, troca_oleo = ?, velas = ?, cabo_velas = ?, bico_injetor = ? where idcli = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPlaca.getText());
            pst.setString(2, txtNome.getText());
            pst.setString(3, txtCorDent.getText());
            pst.setString(4, txtCorAlt.getText());
            pst.setString(5, txtCorArCond.getText());
            pst.setString(6, txtCorBombAgua.getText());
            pst.setString(7, txtFiltroAr.getText());
            pst.setString(8, txtFiltroComb.getText());
            pst.setString(9, txtTrocOleo.getText());
            pst.setString(10, txtVelas.getText());
            pst.setString(11, txtCaboVelas.getText());
            pst.setString(12, txtLimpBicos.getText());
            pst.setString(13, txtCodCli.getText());
            if ((txtNome.getText().isEmpty()) || (txtPlaca.getText().isEmpty()) || (txtCodCli.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados alterados com sucesso.");

                    txtNome.setText(null);
                    txtData.setText(null);
                    txtPlaca.setText(null);
                    txtCorDent.setText(null);
                    txtCorAlt.setText(null);
                    txtCorArCond.setText(null);
                    txtCorBombAgua.setText(null);
                    txtFiltroAr.setText(null);
                    txtFiltroComb.setText(null);
                    txtTrocOleo.setText(null);
                    txtVelas.setText(null);
                    txtCaboVelas.setText(null);
                    txtLimpBicos.setText(null);
                    txtCodCli.setText(null);
                    btnManPreAdicionar.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //método para excluir um dado de manutenção preventiva
    private void excluir_manpre() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que quer excluir os dados de manutenção preventiva?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbmanpre where idcli = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCodCli.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso.");

                    txtNome.setText(null);
                    txtData.setText(null);
                    txtPlaca.setText(null);
                    txtCorDent.setText(null);
                    txtCorAlt.setText(null);
                    txtCorArCond.setText(null);
                    txtCorBombAgua.setText(null);
                    txtFiltroAr.setText(null);
                    txtFiltroComb.setText(null);
                    txtTrocOleo.setText(null);
                    txtVelas.setText(null);
                    txtCaboVelas.setText(null);
                    txtLimpBicos.setText(null);
                    txtCodCli.setText(null);
                    btnManPreAdicionar.setEnabled(true);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCodCli = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCorDent = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCorAlt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCorArCond = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCorBombAgua = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtFiltroAr = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtFiltroComb = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTrocOleo = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtVelas = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtCaboVelas = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtLimpBicos = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        btnManPreAdicionar = new javax.swing.JButton();
        btnManPrePesquisar = new javax.swing.JButton();
        btnManPreAlterar = new javax.swing.JButton();
        btnManPreApagar = new javax.swing.JButton();
        btnManPreImprimir = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Manuntenção Preventiva. Convém comparecer à oficina na quilometragem indicada.");
        setPreferredSize(new java.awt.Dimension(640, 491));

        jLabel1.setText("*Cód Cliente:");

        txtCodCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodCliActionPerformed(evt);
            }
        });

        jLabel2.setText("Data:");

        txtData.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtData.setEnabled(false);

        jLabel3.setText("*Nome:");

        jLabel4.setText("Correia dentada:");

        jLabel5.setText("Correia do alternador:");

        jLabel6.setText("Km");

        jLabel7.setText("Km");

        jLabel8.setText("Correia do ar cond:");

        jLabel9.setText("Km");

        jLabel10.setText("Correia da bomba d'água:");

        jLabel11.setText("Km");

        jLabel12.setText("Filtro de ar:");

        jLabel13.setText("Km");

        jLabel14.setText("Filtro de combustível:");

        jLabel15.setText("Km");

        jLabel16.setText("Troca de óleo:");

        jLabel17.setText("Km");

        jLabel18.setText("Velas:");

        jLabel19.setText("Km");

        jLabel20.setText("Cabos de velas:");

        jLabel21.setText("Km");

        jLabel22.setText("Limpeza de bico:");

        jLabel23.setText("Km");

        jLabel24.setText("*Placa:");

        btnManPreAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/work_space/telas/crud-adicionar-1.jpg"))); // NOI18N
        btnManPreAdicionar.setToolTipText("Adicionar");
        btnManPreAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnManPreAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnManPreAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManPreAdicionarActionPerformed(evt);
            }
        });

        btnManPrePesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/work_space/telas/crud-consultar.jpg"))); // NOI18N
        btnManPrePesquisar.setToolTipText("Consultar");
        btnManPrePesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnManPrePesquisar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnManPrePesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManPrePesquisarActionPerformed(evt);
            }
        });

        btnManPreAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/work_space/telas/crud-alterar.jpg"))); // NOI18N
        btnManPreAlterar.setToolTipText("Alterar");
        btnManPreAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnManPreAlterar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnManPreAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManPreAlterarActionPerformed(evt);
            }
        });

        btnManPreApagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/work_space/telas/crud-apagar.jpg"))); // NOI18N
        btnManPreApagar.setToolTipText("Apagar");
        btnManPreApagar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnManPreApagar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnManPreApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManPreApagarActionPerformed(evt);
            }
        });

        btnManPreImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/work_space/telas/print-2.jpg"))); // NOI18N
        btnManPreImprimir.setToolTipText("Imprimir");
        btnManPreImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnManPreImprimir.setPreferredSize(new java.awt.Dimension(80, 80));
        btnManPreImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManPreImprimirActionPerformed(evt);
            }
        });

        jLabel25.setText("* Campos obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(btnManPreAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(jLabel22))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(95, 95, 95)
                                        .addComponent(jLabel18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(jLabel5))
                                    .addComponent(jLabel10)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel14)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCorAlt)
                                    .addComponent(txtCorBombAgua)
                                    .addComponent(txtFiltroComb)
                                    .addComponent(txtVelas)
                                    .addComponent(txtLimpBicos, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(btnManPrePesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(150, 150, 150)
                                        .addComponent(btnManPreAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                .addComponent(btnManPreApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(btnManPreImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15)
                            .addComponent(jLabel19)
                            .addComponent(jLabel23))
                        .addGap(40, 40, 40))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtCodCli)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel2)
                                .addGap(29, 29, 29)
                                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel24)
                                .addGap(27, 27, 27)
                                .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4))
                    .addComponent(jLabel16)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtCaboVelas, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTrocOleo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFiltroAr, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCorArCond, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCorDent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel17)
                    .addComponent(jLabel13)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtCorDent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtCorArCond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFiltroAr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtTrocOleo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtCaboVelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtCorAlt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtCorBombAgua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtFiltroComb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtVelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtLimpBicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnManPreAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnManPrePesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnManPreAlterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnManPreApagar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnManPreImprimir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );

        setBounds(0, 0, 640, 491);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodCliActionPerformed

    private void btnManPreAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManPreAdicionarActionPerformed
        // Cadastrando dados no relatório de manutenção preventiva
        cadastrar_manpre();
    }//GEN-LAST:event_btnManPreAdicionarActionPerformed

    private void btnManPrePesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManPrePesquisarActionPerformed
        // chamando o método pesquisar_manpre
        pesquisar_id();
    }//GEN-LAST:event_btnManPrePesquisarActionPerformed

    private void btnManPreAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManPreAlterarActionPerformed
        // chamando o método alterar_manpre
        alterar_manpre();
    }//GEN-LAST:event_btnManPreAlterarActionPerformed

    private void btnManPreApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManPreApagarActionPerformed
        // chamando o método para excluir dados de manutenção preventiva
        excluir_manpre();
    }//GEN-LAST:event_btnManPreApagarActionPerformed

    private void btnManPreImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManPreImprimirActionPerformed
        // Gerando um relatório de manutenção preventiva
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão deste relatório de manutenção preventiva?","Atenção!",JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
            //imprimindo relatório com o framework JasperReports
            try {
                //usando a classe JasperPrint para preparar a impressão de um relatório
                JasperPrint print = JasperFillManager.fillReport("C:/Reports/ManutencaoPreventiva.jasper",null, conexao);
                // a linha abaixo exibe o relatório através da classe JasperViewer
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnManPreImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnManPreAdicionar;
    private javax.swing.JButton btnManPreAlterar;
    private javax.swing.JButton btnManPreApagar;
    private javax.swing.JButton btnManPreImprimir;
    private javax.swing.JButton btnManPrePesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtCaboVelas;
    private javax.swing.JTextField txtCodCli;
    private javax.swing.JTextField txtCorAlt;
    private javax.swing.JTextField txtCorArCond;
    private javax.swing.JTextField txtCorBombAgua;
    private javax.swing.JTextField txtCorDent;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtFiltroAr;
    private javax.swing.JTextField txtFiltroComb;
    private javax.swing.JTextField txtLimpBicos;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtTrocOleo;
    private javax.swing.JTextField txtVelas;
    // End of variables declaration//GEN-END:variables
}
