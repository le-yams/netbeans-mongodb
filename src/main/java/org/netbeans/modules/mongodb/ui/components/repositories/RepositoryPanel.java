/*
 * Copyright (C) 2016 Yann D'Isanto
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.netbeans.modules.mongodb.ui.components.repositories;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import org.netbeans.modules.mongodb.resources.Images;
import org.netbeans.modules.mongodb.ui.util.DefaultListModel;
import org.netbeans.modules.mongodb.util.Repository;
import org.netbeans.modules.mongodb.util.Repository.RepositoryItem;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

/**
 *
 * @author Yann D'Isanto
 */
@Messages({
    "ACTION_delete=delete",
    "LBL_panelTitle=load"
})
public class RepositoryPanel<T extends RepositoryItem> extends javax.swing.JPanel {

    private final Repository<T, ?> repository;

    private final DefaultListModel<T> listModel;

    private final DeleteAction deleteAction = new DeleteAction();

    /**
     * Creates new form RepositoryPanel
     */
    public RepositoryPanel(Repository<T, ?> repository) {
        this.repository = repository;
        listModel = new DefaultListModel<>();
        deleteButton.setAction(deleteAction);
        deleteButton.setHideActionText(true);
        initComponents();
        try {
            listModel.addAll(repository.all().values());
            deleteAction.setEnabled(itemsList.getSelectedIndex() > -1);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        itemsList = new javax.swing.JList<>();
        deleteButton = new javax.swing.JButton();

        itemsList.setModel(listModel);
        jScrollPane1.setViewportView(itemsList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deleteButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteButton;
    private javax.swing.JList<T> itemsList;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private final class DeleteAction extends AbstractAction {

        public DeleteAction() {
            super(Bundle.ACTION_delete(), new ImageIcon(Images.CROSS_ICON));
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                T item = itemsList.getSelectedValue();
                if(item != null) {
                repository.remove(item.getKey());
                listModel.remove(itemsList.getSelectedIndex());
                setEnabled(itemsList.getSelectedIndex() > -1);
                }
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }

    }
    
    
    public static <T extends RepositoryItem> T showLoadDialog(Repository<T, ?> repository) {
        RepositoryPanel<T> panel = new RepositoryPanel<>(repository);
        final DialogDescriptor desc = new DialogDescriptor(panel, Bundle.LBL_panelTitle());
        if (NotifyDescriptor.OK_OPTION.equals(DialogDisplayer.getDefault().notify(desc))) {
            return panel.itemsList.getSelectedValue();
        }
        return null;
    }
}
