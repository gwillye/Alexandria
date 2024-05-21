import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private final String ID;
    private final LocalDate dataEmp;
    private LocalDate dataDevolucao;
    private LocalDate dataPrevistaDevolucao;
    private int status;
    private final String ISBN;
    private Sistema sistema;

    public Emprestimo(String ID, String ISBN, LocalDate dataEmp, Sistema sistema) {
        this.ID = ID;
        this.ISBN = ISBN;
        this.dataEmp = dataEmp;
        this.dataPrevistaDevolucao = dataEmp.plusDays(14);
        this.dataDevolucao = null;
        this.status = 0;
        this.sistema = sistema;
        Livro livro = sistema.getLivro(ISBN);
        if (livro != null && livro.getExemplar() > 0) {
            livro.setExemplar(livro.getExemplar() - 1);
        }
    }

    public String getID() {
        return ID;
    }

    public LocalDate getDataEmp() {
        return dataEmp;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public int getStatus() {
        return status;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
        atualizaStatus();
        Livro livro = sistema.getLivro(ISBN);
        if (livro != null) {
            livro.setExemplar(livro.getExemplar() + 1);
        }
    }

    public void atualizaStatus() {
        if (dataDevolucao != null) {
            status = 3;
        } else if (ChronoUnit.DAYS.between(dataPrevistaDevolucao, LocalDate.now()) > 0) {
            status = 2;
        } else if (ChronoUnit.DAYS.between(dataEmp, LocalDate.now()) >= 30) {
            status = 4;
        }
    }

    public void solicitaRenovacao() {
        if (status == 0) {
            dataPrevistaDevolucao = dataPrevistaDevolucao.plusDays(14);
            status = 1;
        }
    }
}
