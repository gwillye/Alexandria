package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTabelaLivro extends AbstractTableModel{
	
	public static final String[] colunas = {"ISBN", "T\u00EDtulo", "Subt\u00EDtulo", "Autor", "Editora",  "G\u00EAnero", "Quantidade "};
	
	private ArrayList<Livro> livros;
	
	

	public ModeloTabelaLivro(ArrayList<Livro> livros) {
		super();
		this.livros = livros;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return livros.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Livro livro = livros.get(rowIndex);
		
		if (columnIndex == 0) {
			return livro.getIsbn();
		} else if (columnIndex == 1) {
			return livro.getTitulo();
		} else if (columnIndex == 2) {
			return livro.getSubtitulo();
		} else if (columnIndex == 3) {
			return livro.getAutor();
		} else if (columnIndex == 5) {
			return livro.getGenero();
		} else if (columnIndex == 4) {
			return livro.getEditora();
		} else if (columnIndex == 6) {
			return livro.getQuantidadeExemplar();
		} else {
			return null;
		}
		
	}
	
	public String getColumnName(int column) {
		return colunas[column];
	}

}
