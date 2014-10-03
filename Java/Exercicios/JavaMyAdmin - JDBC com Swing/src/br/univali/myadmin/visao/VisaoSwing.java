
package br.univali.myadmin.visao;

import br.univali.myadmin.controle.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;




public class VisaoSwing extends javax.swing.JFrame {
    private DefaultMutableTreeNode root;
    private Controlador control;
    private String tabelaSelecionada;
    private String databaseSelecionado;
    
    public VisaoSwing(final Controlador control) {                              // Final para poder usar classes anonimas
        this.control = control;
        initComponents();
        
        
        arvore.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                int caminhos = e.getPath().getPathCount();
                if (caminhos > 2) {
                    tabelaSelecionada = e.getPath().getPath()[2].toString();
                    databaseSelecionado = e.getPath().getPath()[1].toString();
                } else {
                    tabelaSelecionada = null;
                    databaseSelecionado = null;
                }
                control.atualizarDados();
            }
        });
        
        botaoConectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.conectar(campoConexao.getText());
            }
        });
        
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabelaSelecionada != null) control.adicionarLinha();
                else JOptionPane.showMessageDialog(null, "Selecione uma tabela para Inserir");
            }
        });
        
        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabelaSelecionada != null) control.removerLinha();
                else JOptionPane.showMessageDialog(null, "Selecione uma tabela para remover");
            }
        });
        
        arvore.setModel(null);
        this.setVisible(true);
        this.setTitle("JavaMyAdmin - Guilherme Gustavo Gohr Darosci & João Felipe Gonçalves");
        this.setLocationRelativeTo(null);
    }

    public JTable getTabela() {
        return tabela;
    } 
    
    public String[] getColunas() {
        String[] colunas = new String[tabela.getModel().getColumnCount()];
        
        for (int i = 0; i < colunas.length; i++) {
            colunas[i] = tabela.getModel().getColumnName(i);
        }
        
        return colunas;
    }
    
    public String buscarLinha(String nomeColuna) {
        String[] colunas = getColunas();
        for (int i = 0; i < colunas.length; i++) {
            if (colunas[i].equals(nomeColuna)) {
              return (String) tabela.getModel().getValueAt(tabela.getSelectedRow(), i);
            }
        }
        return null;
    }
    
    public String getTabelaSelecionada(){
        return tabelaSelecionada;
    }

    public String getDatabaseSelecionado() {
        return databaseSelecionado;
    }

    public void inserirRaiz(String nomeRaiz) {
        root = new DefaultMutableTreeNode(nomeRaiz);
        DefaultTreeModel modelo = new DefaultTreeModel(root);
        arvore.setModel(modelo);
    }
    
    public void inserirBanco(String nomeBanco, List<String> tabelas) {
        DefaultMutableTreeNode banco = new DefaultMutableTreeNode(nomeBanco);
        for (String string : tabelas) {
            banco.add(new DefaultMutableTreeNode(string));
        }
        
        root.add(banco);
    }
    
    public void preencherTabela(String[] headers, String[][] dados) {        
        DefaultTableModel modeloTabela = (DefaultTableModel) tabela.getModel();
        modeloTabela.setColumnCount(0);
        modeloTabela.getDataVector().clear();
        
        if (dados != null) {
            for (int i = 0; i < headers.length; i++) {
                modeloTabela.addColumn(headers[i], dados[i]);
            }
        } else {
            for (int i = 0; i < headers.length; i++) {
                modeloTabela.addColumn(headers[i]);
            }
          //JOptionPane.showMessageDialog(this, "A tabela não possui dados");
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

        painelPrincipal = new javax.swing.JPanel();
        painelSuperior = new javax.swing.JPanel();
        campoConexao = new javax.swing.JTextField();
        botaoConectar = new javax.swing.JButton();
        splitPane = new javax.swing.JSplitPane();
        painelArvore = new javax.swing.JPanel();
        scrollPaneArvore = new javax.swing.JScrollPane();
        arvore = new javax.swing.JTree();
        painelTabela = new javax.swing.JPanel();
        scrollPaneTabela = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        botaoRemover = new javax.swing.JButton();
        botaoAdicionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        painelSuperior.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Conexão", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N
        painelSuperior.setForeground(new java.awt.Color(153, 153, 153));

        campoConexao.setText("jdbc:mysql://localhost");

        botaoConectar.setText("Conectar");

        javax.swing.GroupLayout painelSuperiorLayout = new javax.swing.GroupLayout(painelSuperior);
        painelSuperior.setLayout(painelSuperiorLayout);
        painelSuperiorLayout.setHorizontalGroup(
            painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(campoConexao)
                .addGap(18, 18, 18)
                .addComponent(botaoConectar)
                .addContainerGap())
        );
        painelSuperiorLayout.setVerticalGroup(
            painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelSuperiorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoConectar))
                .addContainerGap())
        );

        splitPane.setBorder(null);

        painelArvore.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        scrollPaneArvore.setViewportView(arvore);

        javax.swing.GroupLayout painelArvoreLayout = new javax.swing.GroupLayout(painelArvore);
        painelArvore.setLayout(painelArvoreLayout);
        painelArvoreLayout.setHorizontalGroup(
            painelArvoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelArvoreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneArvore, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addContainerGap())
        );
        painelArvoreLayout.setVerticalGroup(
            painelArvoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelArvoreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneArvore, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );

        splitPane.setLeftComponent(painelArvore);

        painelTabela.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrollPaneTabela.setViewportView(tabela);

        botaoRemover.setText("Remover");
        botaoRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverActionPerformed(evt);
            }
        });

        botaoAdicionar.setText("Adicionar");

        javax.swing.GroupLayout painelTabelaLayout = new javax.swing.GroupLayout(painelTabela);
        painelTabela.setLayout(painelTabelaLayout);
        painelTabelaLayout.setHorizontalGroup(
            painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPaneTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                    .addGroup(painelTabelaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoRemover)))
                .addContainerGap())
        );
        painelTabelaLayout.setVerticalGroup(
            painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoAdicionar)
                    .addComponent(botaoRemover))
                .addContainerGap())
        );

        splitPane.setRightComponent(painelTabela);

        javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(painelPrincipalLayout);
        painelPrincipalLayout.setHorizontalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(splitPane)
                    .addComponent(painelSuperior, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        painelPrincipalLayout.setVerticalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(splitPane)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoRemoverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arvore;
    private javax.swing.JButton botaoAdicionar;
    private javax.swing.JButton botaoConectar;
    private javax.swing.JButton botaoRemover;
    private javax.swing.JTextField campoConexao;
    private javax.swing.JPanel painelArvore;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JPanel painelSuperior;
    private javax.swing.JPanel painelTabela;
    private javax.swing.JScrollPane scrollPaneArvore;
    private javax.swing.JScrollPane scrollPaneTabela;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
