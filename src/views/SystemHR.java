package views;

import entities.Employee;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;
import resources.TableDepartments;
import resources.TableEmployee;
import resources.TableSystems;

public class SystemHR extends javax.swing.JFrame {

    private final TableDepartments tbDepartment = new TableDepartments();

    public SystemHR() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbDepTitle1 = new javax.swing.JLabel();
        lbNewDepartment1 = new javax.swing.JLabel();
        tfNewDepartment1 = new javax.swing.JTextField();
        btNewDepartment1 = new javax.swing.JButton();
        btDeleteDep1 = new javax.swing.JButton();
        lbSystemTitle = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jpEmployee = new javax.swing.JPanel();
        lbEmployee = new javax.swing.JLabel();
        lbEmployeeSearch = new javax.swing.JLabel();
        tfEmployeeSearch = new javax.swing.JTextField();
        btNewEmployee = new javax.swing.JButton();
        btDeleteEmployee = new javax.swing.JButton();
        btEmployeeSearch = new javax.swing.JButton();
        cbEmployeeSearchFilter = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbEmployee = new javax.swing.JTable();
        jpDepartments = new javax.swing.JPanel();
        lbDepTitle = new javax.swing.JLabel();
        tfNewDepartment = new javax.swing.JTextField();
        btDeleteDep = new javax.swing.JButton();
        btNewDepartment = new javax.swing.JButton();
        lbNewDepartment = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDepartments = new javax.swing.JTable();
        jpSystems = new javax.swing.JPanel();
        lbSysTitle = new javax.swing.JLabel();
        tfNewSystem = new javax.swing.JTextField();
        btDeleteSystem = new javax.swing.JButton();
        btNewSystem = new javax.swing.JButton();
        lbNewSystem = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSystems = new javax.swing.JTable();
        jpSalary = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbSalary = new javax.swing.JTable();
        lbSalary = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbReports = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tfSearchEmp = new javax.swing.JTextField();
        lbPermissions = new javax.swing.JLabel();
        btSearchEmp = new javax.swing.JButton();
        lbPermName = new javax.swing.JLabel();
        lbPermNameEmp = new javax.swing.JLabel();
        lbPermCPF = new javax.swing.JLabel();
        lbPermCPFEmp = new javax.swing.JLabel();
        lbPermOffice = new javax.swing.JLabel();
        lbPermOfficeEmp = new javax.swing.JLabel();
        lbPermLevel = new javax.swing.JLabel();
        lbPermLevelEmp = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        lbInstitution = new javax.swing.JLabel();
        lbCredits = new javax.swing.JLabel();

        lbDepTitle1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbDepTitle1.setText("Departamentos");

        lbNewDepartment1.setText("Nome do Departamento");

        btNewDepartment1.setText("Adicionar");
        btNewDepartment1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewDepartment1ActionPerformed(evt);
            }
        });

        btDeleteDep1.setText("Excluir Selecionados");
        btDeleteDep1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteDep1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1040, 600));

        lbSystemTitle.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        lbSystemTitle.setText("Sistema de Recursos Humanos");

        lbEmployee.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbEmployee.setText("Funcionários");

        lbEmployeeSearch.setText("Nome do Funcionário");

        btNewEmployee.setText("Novo");
        btNewEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewEmployeeActionPerformed(evt);
            }
        });

        btDeleteEmployee.setText("Excluir Selecionados");

        btEmployeeSearch.setText("Buscar");

        cbEmployeeSearchFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tbEmployee.setModel(new TableEmployee());
        TableColumn officeColumn = tbEmployee.getColumnModel().getColumn(5);
        TableColumn levelColumn = tbEmployee.getColumnModel().getColumn(6);
        officeColumn.setCellEditor(new DefaultCellEditor(((TableEmployee)tbEmployee.getModel()).offices));
        levelColumn.setCellEditor(new DefaultCellEditor(((TableEmployee)tbEmployee.getModel()).levels));

        tbEmployee.setAutoCreateRowSorter(true);
        tbEmployee.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbEmployee);

        javax.swing.GroupLayout jpEmployeeLayout = new javax.swing.GroupLayout(jpEmployee);
        jpEmployee.setLayout(jpEmployeeLayout);
        jpEmployeeLayout.setHorizontalGroup(
            jpEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEmployeeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(jpEmployeeLayout.createSequentialGroup()
                        .addComponent(lbEmployee)
                        .addGap(103, 103, 103)
                        .addComponent(lbEmployeeSearch)
                        .addGap(110, 110, 110)
                        .addComponent(tfEmployeeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbEmployeeSearchFilter, 0, 23, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEmployeeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btNewEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btDeleteEmployee)))
                .addContainerGap())
        );
        jpEmployeeLayout.setVerticalGroup(
            jpEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEmployeeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmployee)
                    .addComponent(lbEmployeeSearch)
                    .addComponent(tfEmployeeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btDeleteEmployee)
                    .addComponent(btNewEmployee)
                    .addComponent(btEmployeeSearch)
                    .addComponent(cbEmployeeSearchFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Funcionários", jpEmployee);

        lbDepTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbDepTitle.setText("Departamentos");

        btDeleteDep.setText("Excluir Selecionados");
        btDeleteDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteDepActionPerformed(evt);
            }
        });

        btNewDepartment.setText("Adicionar");
        btNewDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewDepartmentActionPerformed(evt);
            }
        });

        lbNewDepartment.setText("Nome do Departamento");

        tbDepartments.setModel(new TableDepartments());
        tbDepartments.setColumnSelectionAllowed(true);
        tbDepartments.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbDepartments);

        javax.swing.GroupLayout jpDepartmentsLayout = new javax.swing.GroupLayout(jpDepartments);
        jpDepartments.setLayout(jpDepartmentsLayout);
        jpDepartmentsLayout.setHorizontalGroup(
            jpDepartmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDepartmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDepartmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jpDepartmentsLayout.createSequentialGroup()
                        .addComponent(lbDepTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                        .addComponent(lbNewDepartment)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNewDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btNewDepartment)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btDeleteDep, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpDepartmentsLayout.setVerticalGroup(
            jpDepartmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDepartmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDepartmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDepTitle)
                    .addComponent(tfNewDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btDeleteDep)
                    .addComponent(btNewDepartment)
                    .addComponent(lbNewDepartment))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Departamentos", jpDepartments);

        lbSysTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbSysTitle.setText("Sistemas");

        btDeleteSystem.setText("Excluir Selecionados");
        btDeleteSystem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteSystemActionPerformed(evt);
            }
        });

        btNewSystem.setText("Adicionar");
        btNewSystem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewSystemActionPerformed(evt);
            }
        });

        lbNewSystem.setText("Nome do Sistema");

        tbSystems.setModel(new TableSystems());
        tbSystems.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbSystems);

        javax.swing.GroupLayout jpSystemsLayout = new javax.swing.GroupLayout(jpSystems);
        jpSystems.setLayout(jpSystemsLayout);
        jpSystemsLayout.setHorizontalGroup(
            jpSystemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSystemsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSystemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jpSystemsLayout.createSequentialGroup()
                        .addComponent(lbSysTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                        .addComponent(lbNewSystem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNewSystem, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btNewSystem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btDeleteSystem, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpSystemsLayout.setVerticalGroup(
            jpSystemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSystemsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSystemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSysTitle)
                    .addComponent(tfNewSystem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btDeleteSystem)
                    .addComponent(btNewSystem)
                    .addComponent(lbNewSystem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Sistemas", jpSystems);

        tbSalary.setModel(new TableSystems());
        jScrollPane4.setViewportView(tbSalary);

        lbSalary.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbSalary.setText("Salários");

        javax.swing.GroupLayout jpSalaryLayout = new javax.swing.GroupLayout(jpSalary);
        jpSalary.setLayout(jpSalaryLayout);
        jpSalaryLayout.setHorizontalGroup(
            jpSalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSalaryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                    .addGroup(jpSalaryLayout.createSequentialGroup()
                        .addComponent(lbSalary)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpSalaryLayout.setVerticalGroup(
            jpSalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSalaryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbSalary)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabs.addTab("Salários", jpSalary);

        lbReports.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbReports.setText("Relatórios");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(jTable1);

        jButton1.setText("Imprimir");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbReports)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbReports)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Relatórios", jPanel1);

        lbPermissions.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbPermissions.setText("Permissões");

        btSearchEmp.setText("Buscar");

        lbPermName.setText("Nome");

        lbPermNameEmp.setText("jLabel1");

        lbPermCPF.setText("CPF");

        lbPermCPFEmp.setText("jLabel1");

        lbPermOffice.setText("Cargo");

        lbPermOfficeEmp.setText("jLabel1");

        lbPermLevel.setText("Nível");

        lbPermLevelEmp.setText("jLabel1");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(805, 805, 805)
                .addComponent(lbPermLevelEmp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbPermCPF)
                                .addGap(18, 18, 18)
                                .addComponent(lbPermCPFEmp))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbPermName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbPermNameEmp)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPermLevel)
                            .addComponent(lbPermOffice))
                        .addGap(18, 18, 18)
                        .addComponent(lbPermOfficeEmp)
                        .addGap(182, 182, 182))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lbPermissions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfSearchEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSearchEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPermissions)
                    .addComponent(tfSearchEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSearchEmp))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPermName)
                    .addComponent(lbPermNameEmp)
                    .addComponent(lbPermOffice)
                    .addComponent(lbPermOfficeEmp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPermCPF)
                    .addComponent(lbPermCPFEmp)
                    .addComponent(lbPermLevel)
                    .addComponent(lbPermLevelEmp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Permissões", jPanel2);

        lbInstitution.setText("UFPR - UNIVERSIDADE FEDERAL DO PARANÁ");
        lbInstitution.setEnabled(false);

        lbCredits.setText("Desenvolvido por: Carlos Grispan e Lucas Kindinger");
        lbCredits.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabs)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbSystemTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbInstitution)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbCredits)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbSystemTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbInstitution)
                    .addComponent(lbCredits))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNewDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewDepartmentActionPerformed

        ((TableDepartments) tbDepartments.getModel()).add(tfNewDepartment.getText());
        
        tfNewDepartment.setText(null);

    }//GEN-LAST:event_btNewDepartmentActionPerformed

    private void btDeleteDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteDepActionPerformed

        int rows[] = tbDepartments.getSelectedRows();

        ((TableDepartments) tbDepartments.getModel()).delete(rows);

    }//GEN-LAST:event_btDeleteDepActionPerformed

    private void btDeleteSystemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteSystemActionPerformed
        int rows[] = tbSystems.getSelectedRows();

        ((TableSystems) tbSystems.getModel()).delete(rows);
    }//GEN-LAST:event_btDeleteSystemActionPerformed

    private void btNewSystemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewSystemActionPerformed
        ((TableSystems) tbSystems.getModel()).add(tfNewSystem.getText());
        
        tfNewSystem.setText(null);
    }//GEN-LAST:event_btNewSystemActionPerformed

    private void btNewDepartment1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewDepartment1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btNewDepartment1ActionPerformed

    private void btDeleteDep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteDep1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btDeleteDep1ActionPerformed

    private void btNewEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewEmployeeActionPerformed
        NewEmployee emp = new NewEmployee(this);
        emp.pack();
        emp.setLocationRelativeTo(null);
        emp.setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_btNewEmployeeActionPerformed


    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SystemHR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SystemHR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SystemHR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SystemHR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SystemHR system = new SystemHR();
                system.pack();
                system.setLocationRelativeTo(null);
                system.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDeleteDep;
    private javax.swing.JButton btDeleteDep1;
    private javax.swing.JButton btDeleteEmployee;
    private javax.swing.JButton btDeleteSystem;
    private javax.swing.JButton btEmployeeSearch;
    private javax.swing.JButton btNewDepartment;
    private javax.swing.JButton btNewDepartment1;
    private javax.swing.JButton btNewEmployee;
    private javax.swing.JButton btNewSystem;
    private javax.swing.JButton btSearchEmp;
    private javax.swing.JComboBox cbEmployeeSearchFilter;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel jpDepartments;
    private javax.swing.JPanel jpEmployee;
    private javax.swing.JPanel jpSalary;
    private javax.swing.JPanel jpSystems;
    private javax.swing.JLabel lbCredits;
    private javax.swing.JLabel lbDepTitle;
    private javax.swing.JLabel lbDepTitle1;
    private javax.swing.JLabel lbEmployee;
    private javax.swing.JLabel lbEmployeeSearch;
    private javax.swing.JLabel lbInstitution;
    private javax.swing.JLabel lbNewDepartment;
    private javax.swing.JLabel lbNewDepartment1;
    private javax.swing.JLabel lbNewSystem;
    private javax.swing.JLabel lbPermCPF;
    private javax.swing.JLabel lbPermCPFEmp;
    private javax.swing.JLabel lbPermLevel;
    private javax.swing.JLabel lbPermLevelEmp;
    private javax.swing.JLabel lbPermName;
    private javax.swing.JLabel lbPermNameEmp;
    private javax.swing.JLabel lbPermOffice;
    private javax.swing.JLabel lbPermOfficeEmp;
    private javax.swing.JLabel lbPermissions;
    private javax.swing.JLabel lbReports;
    private javax.swing.JLabel lbSalary;
    private javax.swing.JLabel lbSysTitle;
    private javax.swing.JLabel lbSystemTitle;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tbDepartments;
    private javax.swing.JTable tbEmployee;
    private javax.swing.JTable tbSalary;
    private javax.swing.JTable tbSystems;
    private javax.swing.JTextField tfEmployeeSearch;
    private javax.swing.JTextField tfNewDepartment;
    private javax.swing.JTextField tfNewDepartment1;
    private javax.swing.JTextField tfNewSystem;
    private javax.swing.JTextField tfSearchEmp;
    // End of variables declaration//GEN-END:variables
}
