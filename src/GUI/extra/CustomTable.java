package GUI.extra;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.*;

public class CustomTable extends JTable {
	private static final long serialVersionUID = 1L;

	public CustomTable(TableModel model) {
        super(model);
        setUI(new BasicTableUI() {
            @Override
            public void installUI(JComponent c) {
                super.installUI(c);
                JTable table = (JTable) c;
                table.getTableHeader().setOpaque(false);
                table.getTableHeader().setBackground(ColorUIResource.black);
                table.getTableHeader().setForeground(ColorUIResource.WHITE);
                table.setSelectionBackground(ColorUIResource.black);
                table.setSelectionForeground(ColorUIResource.WHITE);
                table.setOpaque(false);
                table.setForeground(ColorUIResource.WHITE);
                table.setBackground(ColorUIResource.black);
                table.setBorder(BorderFactory.createLineBorder(ColorUIResource.black));
            }
        });
        setShowGrid(false);
    }
}
