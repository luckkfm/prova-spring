INSERT INTO empresas (nome, slug, site, descricao) VALUES
('Aurora Tech', 'aurora-tech', 'aurora.tech', 'Produto de gestão para clínicas. Time pequeno, remoto desde 2020.'),
('Nuvem Rosa', 'nuvem-rosa', 'nuvemrosa.com.br', 'Consultoria de dados para o varejo do Nordeste.'),
('Mareh Digital', 'mareh-digital', 'mareh.digital', 'Agência de produtos digitais em Olinda.'),
('Coral Labs', 'coral-labs', 'corallabs.io', 'Plataforma de educação corporativa.'),
('Tucano Systems', 'tucano-systems', 'tucano.sys.br', 'Integrações e APIs para logística.');

INSERT INTO vagas (titulo, descricao, area, senioridade, localizacao, aceita_iniciante, empresa_id) VALUES
('Pessoa Desenvolvedora Front-end Júnior', 'Telas do produto em React e Next.js, pareando com gente mais experiente.', 'Front-end', 'Júnior', 'Remoto', TRUE, (SELECT id FROM empresas WHERE slug = 'aurora-tech')),
('Estágio em Front-end', 'Primeiro contato com HTML, CSS e JavaScript num time de agência.', 'Front-end', 'Estágio', 'Presencial · Olinda', TRUE, (SELECT id FROM empresas WHERE slug = 'mareh-digital')),
('Pessoa Desenvolvedora Front-end Pleno', 'Dono de features inteiras, do desenho à publicação.', 'Front-end', 'Pleno', 'Remoto', FALSE, (SELECT id FROM empresas WHERE slug = 'nuvem-rosa')),
('Pessoa Desenvolvedora React Júnior', 'Interface da plataforma de cursos, com foco em acessibilidade.', 'Front-end', 'Júnior', 'Híbrido · Recife', TRUE, (SELECT id FROM empresas WHERE slug = 'coral-labs')),
('Pessoa Desenvolvedora Java Júnior', 'APIs REST com Spring Boot, integrando transportadoras.', 'Back-end', 'Júnior', 'Remoto', TRUE, (SELECT id FROM empresas WHERE slug = 'tucano-systems')),
('Pessoa Desenvolvedora Back-end Pleno', 'Serviços de agendamento e faturamento, com fila e cache.', 'Back-end', 'Pleno', 'Remoto', FALSE, (SELECT id FROM empresas WHERE slug = 'aurora-tech')),
('Pessoa Desenvolvedora Node Pleno', 'Serviço de trilhas e progresso, em TypeScript.', 'Back-end', 'Pleno', 'Híbrido · Recife', FALSE, (SELECT id FROM empresas WHERE slug = 'coral-labs')),
('Analista de Dados Júnior', 'SQL, planilhas e construção de painéis para clientes do varejo.', 'Dados', 'Júnior', 'Híbrido · Recife', TRUE, (SELECT id FROM empresas WHERE slug = 'nuvem-rosa')),
('Estágio em Dados', 'Limpeza de base e primeiros painéis, com acompanhamento semanal.', 'Dados', 'Estágio', 'Remoto', TRUE, (SELECT id FROM empresas WHERE slug = 'nuvem-rosa')),
('Pessoa Engenheira de Dados Pleno', 'Pipelines de rastreamento de carga, em Python e SQL.', 'Dados', 'Pleno', 'Remoto', FALSE, (SELECT id FROM empresas WHERE slug = 'tucano-systems')),
('Pessoa Desenvolvedora Mobile Júnior', 'Aplicativos em React Native para clientes da agência.', 'Mobile', 'Júnior', 'Remoto', TRUE, (SELECT id FROM empresas WHERE slug = 'mareh-digital')),
('Pessoa Desenvolvedora Android Júnior', 'Aplicativo de agenda para as clínicas parceiras.', 'Mobile', 'Júnior', 'Presencial · Recife', FALSE, (SELECT id FROM empresas WHERE slug = 'aurora-tech'));
