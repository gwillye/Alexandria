import sqlite3

# Conectar ao banco de dados (cria o banco se não existir)
conn = sqlite3.connect('library.db')
cursor = conn.cursor()

# Dump das tabelas
tables = [
    """CREATE TABLE Pessoa (
        ID INTEGER PRIMARY KEY,
        CPF INTEGER(11) NOT NULL,
        nome TEXT(64) NOT NULL,
        sobrenome TEXT(128) NOT NULL,
        senha TEXT(32) NOT NULL
    );""",
    """CREATE TABLE Endereco (
        ID INTEGER PRIMARY KEY,
        ID_pessoa INTEGER,
        numero_casa INTEGER,
        CEP INTEGER,
        complemento TEXT(256),
        FOREIGN KEY (ID_pessoa) REFERENCES Pessoa(ID)
    );""",
    """CREATE TABLE Cliente (
        ID_cliente INTEGER PRIMARY KEY,
        dataCadastro DATE,
        FOREIGN KEY (ID_cliente) REFERENCES Pessoa(ID)
    );""",
    """CREATE TABLE Funcionario (
        ID_funcionario INTEGER PRIMARY KEY,
        e_admin BOOLEAN,
        FOREIGN KEY (ID_funcionario) REFERENCES Pessoa(ID)
    );""",
    """CREATE TABLE Livro (
        ISBN TEXT PRIMARY KEY,
        editora TEXT(32),
        autor TEXT(128),
        titulo TEXT(128),
        subtitulo TEXT(128),
        genero TEXT(16),
        quantidade_exemplar INTEGER
    );""",
    """CREATE TABLE Exemplar (
        ID_exemplar INTEGER PRIMARY KEY,
        ISBN TEXT,
        edicao INTEGER,
        setor TEXT(32),
        FOREIGN KEY (ISBN) REFERENCES Livro(ISBN)
    );""",
    """CREATE TABLE Emprestimo (
        ID INTEGER PRIMARY KEY,
        ID_cliente INTEGER,
        ID_funcionario INTEGER,
        FOREIGN KEY (ID_cliente) REFERENCES Cliente(ID_cliente),
        FOREIGN KEY (ID_funcionario) REFERENCES Funcionario(ID_funcionario)
    );""",
    """CREATE TABLE ItemEmprestimo (
        ID_exemplar INTEGER,
        ID_emprestimo INTEGER,
        data_emprestimo DATE,
        data_devolucao DATE,
        data_prevista_devolucao DATE,
        status INTEGER,
        PRIMARY KEY (ID_exemplar, ID_emprestimo),
        FOREIGN KEY (ID_exemplar) REFERENCES Exemplar(ID_exemplar),
        FOREIGN KEY (ID_emprestimo) REFERENCES Emprestimo(ID)
    );"""
]

# Executar as queries para criar as tabelas
for table in tables:
    cursor.execute(table)

# Confirmar as mudanças e fechar a conexão
conn.commit()
conn.close()
