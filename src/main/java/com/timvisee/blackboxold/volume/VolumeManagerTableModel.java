package com.timvisee.blackboxold.volume;

import com.timvisee.blackbox.volume.Volume;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class VolumeManagerTableModel implements TableModel {
	
	private VolumeManager v = null;
	
    private List<TableModelListener> listeners = new ArrayList<TableModelListener>();
    
	public VolumeManagerTableModel(VolumeManager v) {
		this.v = v;
	}

    public Volume getElementAt(int i) {
        return this.v.getVolumes().get(i);
    }

	@Override
	public void addTableModelListener(TableModelListener l) {
		this.listeners.add(l);
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		this.listeners.remove(l);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex) {
		case 0:
			return "Volume";
			
		case 1:
			return "Enabled";
			
		case 2:
			return "Type";
		
		default:
			return null;
		}
	}

	@Override
	public int getRowCount() {
		return this.v.getVolumesCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// Get the volume
		Volume v = this.v.getVolumes().get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return v.getName();
			
		case 1:
			return v.isEnabled() ? "Enabled" : "Disabled";
			
		case 2:
			return v.getVolumeType().getName();
			
		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return (columnIndex == 0);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// Get the profile
		Volume v = this.v.getVolumes().get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			try {
				v.setName((String) aValue);
			} catch(ClassCastException e) { }
			break;
			
		default:
			return;
		}
	}
}
