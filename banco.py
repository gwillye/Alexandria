import sqlite3
from datetime import date, timedelta
import random

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

# Populando as tabelas
# Pessoas
pessoas = [
    (i, 12345678900 + i, f'Nome{i}', f'Sobrenome{i}', f'password{i}') for i in range(1, 31)
]

for pessoa in pessoas:
    cursor.execute("INSERT INTO Pessoa (ID, CPF, nome, sobrenome, senha) VALUES (?, ?, ?, ?, ?)", pessoa)

# Endereços
enderecos = [
    (i, random.randint(1, 30), random.randint(1, 1000), 10000000 + i, f'Complemento{i}') for i in range(1, 26)
]

for endereco in enderecos:
    cursor.execute("INSERT INTO Endereco (ID, ID_pessoa, numero_casa, CEP, complemento) VALUES (?, ?, ?, ?, ?)", endereco)

# Clientes
clientes = [
    (i, date.today() - timedelta(days=random.randint(1, 365))) for i in range(1, 21)
]

for cliente in clientes:
    cursor.execute("INSERT INTO Cliente (ID_cliente, dataCadastro) VALUES (?, ?)", cliente)

# Funcionários
funcionarios = [
    (i + 20, int(i > 26)) for i in range(1, 9)
]

for funcionario in funcionarios:
    cursor.execute("INSERT INTO Funcionario (ID_funcionario, e_admin) VALUES (?, ?)", funcionario)

# Livros e Exemplares
livros = [
    ('9780140449136', 'Penguin Classics', 'Walter Scott', 'Ivanhoé', '', 'Fiction', 5),
    ('9780141319136', 'Hyperion Books', 'Rick Riordan', 'Percy Jackson', 'The Lightning Thief', 'Fantasy', 4),
    ('9780060930219', 'Harper Perennial', 'Alexandre Dumas', 'Os três Mosqueteiros', '', 'Adventure', 3),
    ('9780451208637', 'Penguin Classics', 'Dante Alighieri', 'A Divina Comédia', '', 'Poetry', 3),
    ('9780143128540', 'Simon & Schuster', 'William Shakespeare', 'Romeu e Julieta', '', 'Tragedy', 5),
    ('9780140714548', 'Simon & Schuster', 'William Shakespeare', 'Hamlet', '', 'Tragedy', 5),
    ('9780451527918', 'Signet Classics', 'Victor Hugo', 'O Corcunda de Notre Dame', '', 'Historical', 4),
    ('9780140280197', 'Viking', 'Robert Greene', 'As 48 leis do poder', '', 'Self-help', 3),
    ('9780310950974', 'Zondervan', 'God', 'Bíblia Sagrada', '', 'Religion', 3),
    ('9780828007933', 'Pacific Press', 'Ellen G. White', 'O Grande Conflito', '', 'Religion', 4),
    ('9780804137386', 'Crown Business', 'Greg McKeown', 'Essencialismo', '', 'Self-help', 2),
    ('9780062844061', 'HarperCollins', 'Ellen G. White', 'Orientação da Criança', '', 'Religion', 2),
    ('9780062348494', 'HarperOne', 'John Bunyan', 'O Peregrino', '', 'Allegory', 2),
    ('9780060850524', 'Harper Perennial', 'Aldous Huxley', 'Admirável Mundo Novo', '', 'Dystopian', 5),
    ('9780451524931', 'Signet Classics', 'George Orwell', '1984', '', 'Dystopian', 5)
]

for livro in livros:
    cursor.execute("INSERT INTO Livro (ISBN, editora, autor, titulo, subtitulo, genero, quantidade_exemplar) VALUES (?, ?, ?, ?, ?, ?, ?)", livro)

# Adicionando exemplares
exemplares = []
exemplar_id = 1
for livro in livros:
    num_exemplares = livro[6]
    for edicao in range(1, num_exemplares + 1):
        exemplares.append((exemplar_id, livro[0], edicao, 'A'))
        exemplar_id += 1

for exemplar in exemplares:
    cursor.execute("INSERT INTO Exemplar (ID_exemplar, ISBN, edicao, setor) VALUES (?, ?, ?, ?)", exemplar)

# Empréstimos
emprestimos = [
    (i, random.randint(1, 20), random.randint(21, 28)) for i in range(1, 11)
]

for emprestimo in emprestimos:
    cursor.execute("INSERT INTO Emprestimo (ID, ID_cliente, ID_funcionario) VALUES (?, ?, ?)", emprestimo)

# Itens de Empréstimos
today = date.today()
itens_emprestimos = [
    (i, 1, today, None, today + timedelta(days=14), 0) for i in range(1, 9)
]
itens_emprestimos.append((9, 9, today - timedelta(days=20), None, today - timedelta(days=6), 3))
itens_emprestimos.append((10, 10, today - timedelta(days=10), None, today + timedelta(days=4), 1))

for item in itens_emprestimos:
    cursor.execute("INSERT INTO ItemEmprestimo (ID_exemplar, ID_emprestimo, data_emprestimo, data_devolucao, data_prevista_devolucao, status) VALUES (?, ?, ?, ?, ?, ?)", item)

# Confirmar as mudanças e fechar a conexão
conn.commit()
conn.close()
