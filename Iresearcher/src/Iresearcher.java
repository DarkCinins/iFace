import java.util.Scanner;

public class Iresearcher
{
    private static void errorThree(int option)
    {
        if(option > 3 || option < 1) System.out.printf("Opção Inválida.%n%n");
    }
    private static void errorFour(int option)
    {
        if(option > 4 || option < 1) System.out.printf("Opção Inválida.%n%n");
    }
    private static void errorFive(int option)
    {
        if(option > 5 || option < 1) System.out.printf("Opção Inválida.%n%n");
    }
    private static void errorSeven(int option)
    {
        if(option > 7 || option < 1) System.out.printf("Opção Inválida.%n%n");
    }
    private static void memset(int[][] friends, int[][] requests, int[] deletedAccounts, int maxUsers)
    {
        for(int i = 0; i < maxUsers; i++)
        {
            deletedAccounts[i] = -1;
            for(int j = 0; j < maxUsers; j++)
            {
                friends[i][j] = -1;
                requests[i][j] = -1;
            }
        }
    }
    private static int homeMenu()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Bem vindo ao iResearcher!");
        System.out.println();
        System.out.println("1 - Entrar");
        System.out.println("2 - Cadastrar-se");
        System.out.println("3 - Sair");
        return input.nextInt();
    }
    private static int login(String[][] users, int count, int[] deletedAccounts)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite seu usuário:");
        String user = input.nextLine();
        System.out.println("Digite sua senha:");
        String password = input.nextLine();
        for(int i = 0; i < count; i++)
            if(!checkDeletedAccount(deletedAccounts, i) && users[i][1].equals(user) && users[i][2].equals(password)) return i;
        System.out.printf("Usuário e/ou senha incorretos.%n%n");
        return -1;
    }
    private static boolean register(String[][] user, int pos, int[] deletedAccounts)
    {
        if(pos == user.length)
        {
            System.out.println("Desculpe, nossos servidores atingiram o limite de usuários.");
            System.out.printf("Por favor aguarde alguma conta ser excluída ou a expansão de nossos servidores.%n%n");
            return false;
        }
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("Digite seu nome (até 50 caracteres):");
        user[pos][0] = input.nextLine();
        int check;
        do
        {
            System.out.println("Digite seu usuário de LogIn (até 50 caracteres):");
            String trying = input.nextLine();
            check = -1;
            for(int i = 0; i < pos; i++)
                if(user[i][1].equals(trying)) check = i;
            if(check == -1 || checkDeletedAccount(deletedAccounts, check)) user[pos][1] = trying;
            else System.out.printf("Usuário já existe.%n%n");
        }while(check != -1);
        System.out.println("Digite sua senha (até 50 caracteres):");
        user[pos][2] = input.nextLine();
        return true;
    }
    private static int loginMenu(String user)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Bem vindo " + user + "!");
        System.out.println();
        System.out.println("1 - Perfil");
        System.out.println("2 - Amigos");
        System.out.println("3 - Mensagens");
        System.out.println("4 - Comunidades");
        System.out.println("5 - Sair");
        return input.nextInt();
    }
    private static int profileMenu()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("1 - Ver Perfil");
        System.out.println("2 - Editar Perfil");
        System.out.println("3 - Adicionar Atributo de Perfil");
        System.out.println("4 - Alterar Atributo de Perfil");
        System.out.println("5 - Remover Atributo de Perfil");
        System.out.println("6 - Excluir Conta");
        System.out.println("7 - Voltar");
        return input.nextInt();
    }
    private static void getProfile(String[][] users, String[][] profiles, int user)
    {
        System.out.println("Nome: " + users[user][0]);
        System.out.println("Usuário: " + users[user][1]);
        System.out.println("Senha: ******");
        for(int i = 0; i < profiles[user].length; i++)
            if(profiles[user][i] != null) System.out.println(profiles[user][i] + ": " + profiles[user][++i]);
        System.out.println();
    }
    private static int editMainProfileMenu()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("1 - Editar Nome");
        System.out.println("2 - Editar Usuário");
        System.out.println("3 - Editar Senha");
        System.out.println("4 - Voltar");
        return input.nextInt();
    }
    private static void editMainProfile(String[][] users, int user, int option)
    {
        if(option > 0 && option < 4)
        {
            Scanner input = new Scanner(System.in);
            System.out.println("Digite sua senha atual:");
            String password = input.nextLine();
            if(users[user][2].equals(password))
            {
                switch(option)
                {
                    case 1:
                        System.out.println("Digite seu novo nome:");
                        users[user][0] = input.nextLine();
                        break;
                    case 2:
                        System.out.println("Digite seu novo usuário:");
                        users[user][1] = input.nextLine();
                        break;
                    case 3:
                        System.out.println("Digite sua nova senha:");
                        users[user][2] = input.nextLine();
                        break;
                }
                System.out.printf("Perfil alterado com sucesso.%n%n");
            }
            else
            {
                System.out.printf("Senha incorreta.%n");
            }
        }
    }
    private static boolean setAttributeProfile(String[][] profiles, int user)
    {
        if(profiles[user][profiles[user].length - 1] == null)
        {
            for(int i = 0; i < profiles[user].length; i += 2)
                if(profiles[user][i] == null)
                {
                    Scanner input = new Scanner(System.in);
                    System.out.println("Digite o nome do atributo:");
                    profiles[user][i] = input.nextLine();
                    System.out.println("Digite a descrição do atributo:");
                    profiles[user][i + 1] = input.nextLine();
                    return true;
                }
        }
        System.out.printf("Você atingiu o limite de atributos por perfil.%n%n");
        return false;
    }
    private static boolean editAttributeProfile(String[][] profiles, int user)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o título do atributo que deseja alterar:");
        String attribute = input.nextLine();
        for(int i = 0; i < profiles[user].length; i += 2)
        {
            if(profiles[user][i] != null && profiles[user][i].equals(attribute))
            {
                System.out.println("1 - Alterar título do atributo");
                System.out.println("2 - Alterar descrição do atributo");
                System.out.println("3 - Voltar");
                switch(input.nextInt())
                {
                    case 1:
                        System.out.println("Digite o novo título do atributo:");
                        input.nextLine();
                        profiles[user][i] = input.nextLine();
                        return true;
                    case 2:
                        System.out.println("Digite a nova descrição do atributo:");
                        input.nextLine();
                        profiles[user][i+1] = input.nextLine();
                        return true;
                    case 3:
                        return false;
                }
                System.out.printf("Opção inválida.%n%n");
                i -= 2;
            }
        }
        System.out.printf("Atributo não encontrado.%n%n");
        return false;
    }
    private static boolean removeAttributeProfile(String[][] profiles, int user)
    {
        Scanner input = new Scanner(System.in);
        int i, j;
        System.out.println("Digite o título do atributo que deseja remover:");
        String attribute = input.nextLine();
        for(i = 0; i < profiles[user].length; i += 2)
        {
            if(profiles[user][i] != null && profiles[user][i].equals(attribute))
            {
                for(j = i; j < profiles[user].length; j++)
                {
                    if(j == profiles[user].length - 1)
                    {
                        profiles[user][i] = profiles[user][j - 1];
                        profiles[user][i + 1] = profiles[user][j];
                        profiles[user][j - 1] = null;
                        profiles[user][j] = null;
                        return true;
                    }
                    else if(profiles[user][j] == null)
                    {
                        profiles[user][i] = profiles[user][j - 2];
                        profiles[user][i + 1] = profiles[user][j - 1];
                        profiles[user][j - 2] = null;
                        profiles[user][j - 1] = null;
                        return true;
                    }
                }
            }
        }
        System.out.printf("Atributo não encontrado.%n%n");
        return false;
    }
    private static void deleteAccount(int[] deletedAccounts, int account)
    {
        for(int i = 0; i < deletedAccounts.length; i++)
        {
            if(deletedAccounts[i] == -1)
            {
                deletedAccounts[i] = account;
                System.out.printf("Conta excluída com sucesso.%n%n");
                break;
            }
        }
    }
    private static boolean checkDeletedAccount(int[] deletedAccounts, int account)
    {
        for(int x : deletedAccounts)
        {
            if(x == -1) break;
            if(x == account) return true;
        }
        return false;
    }
    private static int friendsMenu()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("1 - Amigos");
        System.out.println("2 - Solicitações de Amizade");
        System.out.println("3 - Adicionar Amigo");
        System.out.println("4 - Excluir Amigo");
        System.out.println("5 - Voltar");
        return input.nextInt();
    }
    private static void getFriends(int[][] friends, int user, String[][] users, int[] deletedAccounts)
    {
        boolean foreverAlone = true;
        for(int i = 0; i < friends[user].length; i++)
        {
            if(friends[user][i] != -1 && !checkDeletedAccount(deletedAccounts, friends[user][i]))
            {
                foreverAlone = false;
                System.out.println(users[friends[user][i]][0]);
            }
        }
        if(foreverAlone) System.out.printf("Você não tem amigos.%n%n");
    }
    private static void getRequests(String[][] users, int[][] requests, int[][] friends, int user, int[] deletedAccounts)
    {
        Scanner input = new Scanner(System.in);
        int i, option;
        do
        {
            for(i = 0; i < requests.length; i++)
            {
                if(requests[user][i] != -1 && !checkDeletedAccount(deletedAccounts, requests[user][i])) System.out.println((i+1) + " - " + users[requests[user][i]][0] + " deseja ser seu amigo!");
                else
                {
                    if(i == 0) System.out.println("Sem solicitações.");
                    System.out.println((i+1) + " - Voltar");
                    break;
                }
            }
            option = input.nextInt() - 1;
            if(option < 0 || option > i) System.out.printf("Opção Inválida.%n%n");
            else if(option != i)
            {
                int decision;
                do
                {
                    System.out.println("1 - Aceitar pedido");
                    System.out.println("2 - Recusar pedido");
                    System.out.println("3 - Voltar");
                    decision = input.nextInt();
                    if(decision < 1 || decision > 3) System.out.printf("Opção Inválida.%n%n");
                    switch(decision)
                    {
                        case 1:
                            for(int j = 0; j < friends[requests[user][option]].length; j++)
                            {
                                if(friends[requests[user][option]][j] == -1)
                                {
                                    friends[requests[user][option]][j] = user;
                                    break;
                                }
                            }
                            for(int j = 0; j < friends[user].length; j++)
                            {
                                if(friends[user][j] == -1)
                                {
                                    friends[user][j] = requests[user][option];
                                    break;
                                }
                            }
                            requests[user][option] = requests[user][i - 1];
                            requests[user][i - 1] = -1;
                            System.out.printf("Amigo adicionado com sucesso.%n%n");
                            decision = 3;
                            break;
                        case 2:
                            requests[user][option] = requests[user][i - 1];
                            requests[user][i - 1] = -1;
                            System.out.printf("Pedido recusado com sucesso.%n%n");
                            decision = 3;
                            break;
                    }
                }while(decision != 3);
            }
        }while(option != i);
    }
    private static int check(String[][] users, int[][] requests, int[][] friends, int user, String friend)
    {
        if(users[user][0].equals(friend))
        {
            System.out.printf("Não é possível enviar convite de amizade para si mesmo.%n%n");
            return -1;
        }
        for(int i = 0; i < friends[user].length; i++)
        {
            if(users[i][0] != null && users[i][0].equals(friend))
            {
                for(int j = 0; j < requests[i].length; j++)
                {
                    if(requests[i][j] == user)
                    {
                        System.out.println("Você já enviou um pedido de amizade para " + friend + " anteriormente.");
                        System.out.printf("Por favor aguarde a resposta do usuário.%n%n");
                        return -1;
                    }
                }
            }
            if(friends[user][i] != -1 && users[friends[user][i]][0].equals(friend))
            {
                System.out.printf(friend + " já está em sua lista de amigos.%n%n");
                return -1;
            }
            if(requests[user][i] != -1 && users[requests[user][i]][0].equals(friend))
            {
                System.out.println(friend + " já lhe enviou uma solicitação de amizade.");
                System.out.printf("Por favor vá na aba de solcitações para adiciona-lo.%n%n");
                return -1;
            }
        }
        for(int i = 0; i < users.length; i++)
            if(users[i][0] != null && users[i][0].equals(friend)) return i;
        System.out.printf("Usuário não existe em nossa base de dados.%n%n");
        return -1;
    }
    private static void setRequests(String[][] users, int[][] requests, int[][] friends, int user, int[] deletedAccounts)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o nome do usuário para enviar um pedido de amizade:");
        String friend = input.nextLine();
        int result = check(users, requests, friends, user, friend);
        if(result != -1)
        {
            int check = 0;
            for(int i = 0; i < users.length; i++)
            {
                if(users[i][0] == null) break;
                if(users[i][0].equals(friend)) check = i;
            }
            if(!checkDeletedAccount(deletedAccounts, check))
            {
                for(int i = 0; i < requests[result].length; i++)
                {
                    if(requests[result][i] == -1)
                    {
                        requests[result][i] = user;
                        System.out.printf("Solicitação enviada com sucesso.%n%n");
                        break;
                    }
                }
            }
        }
    }
    private static void removeFriend(String[][] users, int[][] friends, int user)
    {
        int i, j, k, l, h;
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o nome do amigo a ser removido:");
        String friend = input.nextLine();
        for(i = 0; i < users.length; i++)
        {
            if(users[i][0] != null && users[i][0].equals(friend))
            {
                for(j = 0; j < friends[user].length; j++)
                {
                    if(friends[user][j] == i)
                    {
                        for(k = j; k < friends[user].length; k++)
                        {
                            if(friends[user][k] == -1)
                            {
                                friends[user][j] = friends[user][k - 1];
                                friends[user][k - 1] = -1;
                                for(l = 0; l < friends[i].length; l++)
                                {
                                    if(friends[i][l] == user)
                                    {
                                        for(h = l; h < friends[i].length; h++)
                                        {
                                            if(friends[i][h] == -1)
                                            {
                                                friends[i][l] = friends[i][h - 1];
                                                friends[i][h - 1] = -1;
                                                System.out.printf("Amigo excluído com sucesso.%n%n");
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if(j == friends[user].length)System.out.printf(friend + " não está na sua lista de amigos.%n%n");
                break;
            }
        }
        if(i == users.length)System.out.printf("Usuário não existe em nossa base de dados.%n%n");
    }
    private static int messageMenu()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("1 - Enviar Mensagem");
        System.out.println("2 - Ver Mensagens");
        System.out.println("3 - Voltar");
        return input.nextInt();
    }
    private static void sendMessage(String[][] users, String[][][] messages, int user, int[] deletedAccounts)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o nome do destinatário:");
        String receiver = input.nextLine();
        for(int i = 0; i < users.length; i++)
        {
            if(users[i][0] == null || checkDeletedAccount(deletedAccounts, i))
            {
                System.out.printf("Usuário não existe.%n%n");
                break;
            }
            if(users[i][0].equals(receiver))
            {
                for(int k = 0; k < messages[i][user].length; k++)
                {
                    if(messages[i][user][k] == null)
                    {
                        System.out.println("Digite sua mensagem:");
                        messages[i][user][k] = input.nextLine();
                        System.out.println("Mensagem enviada com sucesso.");
                        System.out.println();
                        break;
                    }
                }
                break;
            }
        }
    }
    private static void getMessages(String[][] users, String[][][] messages, int user, int[] deletedAccounts)
    {
        for(int j = 0; j < users.length; j++)
        {
            if(users[j][0] == null) break;
            if(messages[user][j][0] != null && !checkDeletedAccount(deletedAccounts, j)) System.out.println(users[j][0] + ":");
            for(int k = 0; k < messages[user][j].length; k++)
            {
                if(messages[user][j][k] == null ||  checkDeletedAccount(deletedAccounts, j)) break;
                System.out.println(messages[user][j][k]);
            }
            System.out.println();
        }
    }
    private static int communityMenu()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("1 - Criar Comunidade");
        System.out.println("2 - Comunidades Criadas");
        System.out.println("3 - Comunidades que Participo");
        System.out.println("4 - Participar de uma Comunidade");
        System.out.println("5 - Enviar Mensagem em uma Comunidade");
        System.out.println("6 - Visualizar Mensagens das Comunidades");
        System.out.println("7 - Voltar");
        return input.nextInt();
    }
    private static boolean checkCommunityLimit(String[] communities)
    {
        if(communities[communities.length - 1] == null) return true;
        System.out.printf("O limite de comunidades suportado por nossos servidores foi atingido.%n%n");
        return false;
    }
    private static void createCommunity(String[] communities, String[] communitiesDescription, String[][]communitiesUsers, String user)
    {
        if(checkCommunityLimit(communities))
        {
            Scanner input = new Scanner(System.in);
            System.out.println("Digite o nome da comunidade a ser criada:");
            String community = input.nextLine();
            System.out.println("Digite a descrição da comunidade:");
            String description = input.nextLine();
            for(int i = 0; i < communities.length; i++)
            {
                if(communities[i] == null)
                {
                    communities[i] = community;
                    communitiesDescription[i] = description;
                    communitiesUsers[i][0] = user;
                    System.out.printf("Comunidade criada com sucesso.%n%n");
                    break;
                }
            }
        }
    }
    private static void communitiesCreatedByMe(String[] communities, String[] communitiesDescription, String[][] communitiesUsers, String user)
    {
        boolean comunity = false;
        for(int i = 0; i < communities.length; i++)
        {
            if(communities[i] != null)
            {
                if(communitiesUsers[i][0].equals(user))
                {
                    System.out.println(communities[i] + ": " + communitiesDescription[i]);
                    System.out.println();
                    comunity = true;
                }
            }
            else break;
        }
        if(!comunity) System.out.printf("Você não criou nenhuma comunidade.%n%n");
    }
    private static void myCommunityRequests(String[] communities, String[][] communitiesRequests, String[][] communityUsers, String user)
    {
        int k = 1;
        Scanner input = new Scanner(System.in);
        System.out.printf("Solicitações:%n%n");
        for(int i = 0; i < communities.length; i++)
        {
            if(communities[i] == null) break;
            if(communityUsers[i][0].equals(user))
            {
                for(int j = 0; j < communitiesRequests[i].length; j++)
                {
                    if(communitiesRequests[i][j] == null) break;
                    System.out.println(k + " - " + communitiesRequests[i][j] + " deseja participar da comunidade " + communities[i]);
                    k++;
                }
            }
        }
        if(k == 1) System.out.printf("Não há solicitações.%n%n");
        System.out.println(k + " - Voltar");
        int option;
        int l = 1;
        do
        {
            option = input.nextInt();
            if(option > k || option < 1) System.out.printf("Opção Inválida.%n%n");
            else if(option != k)
            {
                for(int i = 0; i < communities.length; i++)
                {
                    if(communities[i] == null) break;
                    if(communityUsers[i][0].equals(user))
                    {
                        for(int j = 0; j < communitiesRequests[i].length; j++)
                        {
                            if(communitiesRequests[i][j] == null) break;
                            if(option == l)
                            {
                                do
                                {
                                    System.out.println("1 - Aceitar");
                                    System.out.println("2 - Rejeitar");
                                    option = input.nextInt();
                                    if(option != 1 && option != 2) System.out.printf("Opção Inválida.%n%n");
                                    switch(option)
                                    {
                                        case 1:
                                            for(int h = 0; h < communityUsers[i].length; h++)
                                            {
                                                if(communityUsers[i][h] == null)
                                                {
                                                    communityUsers[i][h] = communitiesRequests[i][j];
                                                    for(int g = j; g < communitiesRequests[i].length; g++)
                                                    {
                                                        if(communitiesRequests[i][g] == null)
                                                        {
                                                            communitiesRequests[i][j] = communitiesRequests[i][g - 1];
                                                            communitiesRequests[i][g - 1] = null;
                                                            System.out.printf("Solicitação aceita com sucesso.%n%n");
                                                            break;
                                                        }
                                                    }
                                                    break;
                                                }
                                            }
                                            break;
                                        case 2:
                                            for(int g = j; g < communitiesRequests[i].length; g++)
                                            {
                                                if(communitiesRequests[i][g] == null)
                                                {
                                                    communitiesRequests[i][j] = communitiesRequests[i][g - 1];
                                                    communitiesRequests[i][g - 1] = null;
                                                    System.out.printf("Solicitação rejeitada com sucesso.%n%n");
                                                    break;
                                                }
                                            }
                                            break;
                                    }
                                }while(option < 1 || option > 2);
                                break;
                            }
                            l++;
                        }
                        break;
                    }
                }
                break;
            }
        }while(option != k);
    }
    private static void myCommunities(String[] communities, String[] communitiesDescription, String[][] communitiesUsers, String user)
    {
        boolean community = false;
        for(int i = 0; i < communitiesUsers.length; i++)
        {
            for(int j = 0; j < communitiesUsers[i].length; j++)
            {
                if(communitiesUsers[i][j] == null) break;
                if(communitiesUsers[i][j].equals(user))
                {
                    System.out.println("Comunidade: " + communities[i]);
                    System.out.printf("Descrição: " + communitiesDescription[i] + "%n%n");
                    community = true;
                    break;
                }
            }
        }
        if(!community) System.out.printf("Você não participa de nenhuma comunidade.%n%n");
    }
    private static boolean checkCommunity(String[] communities, String[][] communitiesUsers, String community, String user)
    {
        for(int i = 0; i < communities.length; i++)
        {
            if(communities[i] == null) break;
            if(communities[i].equals(community))
            {
                for(int j = 0; j < communitiesUsers[i].length; j++)
                {
                    if(communitiesUsers[i][j] == null) break;
                    if(communitiesUsers[i][j].equals(user))
                    {
                        System.out.printf("Você já faz parte desta comunidade.%n%n");
                        return true;
                    }
                }
                return false;
            }
        }
        System.out.printf("Comunidade não existe em nossa base de dados.%n%n");
        return true;
    }
    private static boolean checkRequests(String[] communities, String[][] communitiesRequests, String community, String user)
    {
        for(int i = 0; i < communities.length; i++)
        {
            if(communities[i] == null) break;
            if(communities[i].equals(community))
            {
                for(int j = 0; j < communitiesRequests[i].length; j++)
                {
                    if(communitiesRequests[i][j] == null) return false;
                    if(communitiesRequests[i][j].equals(user))
                    {
                        System.out.println("Você já enviou uma solicitação para esta comunidade anterioremente.");
                        System.out.printf("Por favor aguarde a confirmação do administrador da comunidade.%n%n");
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
    private static void getCommunity(String[] communities, String[][] communitiesUsers, String[][] communitiesRequests, String user)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o nome da comunidade na qual deseja entrar:");
        String community = input.nextLine();
        if(!checkCommunity(communities, communitiesUsers, community, user) && !checkRequests(communities, communitiesRequests, community, user))
        {
            int i;
            for(i = 0; i < communities.length; i++)
            {
                if(communities[i].equals(community))
                {
                    for(int j = 0; j < communitiesRequests[i].length; j++)
                    {
                        if(communitiesRequests[i][j] == null)
                        {
                            communitiesRequests[i][j] = user;
                            System.out.printf("Solicitação enviada. Aguarde a resposta do administrador da comunidade.%n%n");
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
    private static void messageToCommunity(String[] communities, String[][] communitiesUsers, String[][][] communityMessages, String user)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o nome da comunidade para a qual deseja enviar uma mensagem.");
        String community = input.nextLine();
        for(int i = 0; i < communities.length; i++)
        {
            if(communities[i] == null)
            {
                System.out.printf("Comunidade '" + community + "' não existe.%n%n");
                break;
            }
            if(communities[i].equals(community))
            {
                for(int j = 0; j < communitiesUsers[i].length; j++)
                {
                    if(communitiesUsers[i][j] == null)
                    {
                        System.out.printf("Você não faz parte dessa comunidade.%n%n");
                        break;
                    }
                    if(communitiesUsers[i][j].equals(user))
                    {
                        if(communityMessages[i][j][communityMessages[i][j].length - 1] == null)
                        {
                            for(int k = 0; k < communityMessages[i][j].length; k++)
                            {
                                if(communityMessages[i][j][k] == null)
                                {
                                    System.out.println("Digite sua mensagem:");
                                    communityMessages[i][j][k] = input.nextLine();
                                    System.out.printf("Mensagem enviada com sucesso.%n%n");
                                    break;
                                }
                            }
                        }
                        else System.out.printf("Limite de mensagens por comunidade atingido.%n%n");
                        break;
                    }
                }
                break;
            }
        }
    }
    private static boolean checkCommunityMember(String[] communities, String[][] communityUsers, String user)
    {
        for(int i = 0; i < communities.length; i++)
        {
            if(communities[i] == null) break;
            for(int j = 0; j < communityUsers[i].length; j++)
            {
                if(communityUsers[i][j] == null) break;
                if(communityUsers[i][j].equals(user)) return true;
            }
        }
        return false;
    }
    private static void getCommunityMessages(String[] communities, String[][] communityUsers, String[][][] communityMessages, String user, int[] deletedAccounts)
    {
        int i, j, k;
        for(i = 0; i < communities.length; i++)
        {
            if(communities[i] == null) break;
            if(checkCommunityMember(communities, communityUsers, user))
            {
                System.out.println(communities[i] + ":");
                for(j = 0; j < communityUsers[i].length; j++)
                {
                    if(communityUsers[i][j] == null) break;
                    if(communityMessages[i][j][0] != null && !checkDeletedAccount(deletedAccounts, j))
                    {
                        System.out.println("  " + communityUsers[i][j] + ":");
                        for(k = 0; k < communityMessages[i][j].length; k++)
                        {
                            if(communityMessages[i][j][k] == null) break;
                            System.out.println("    " + communityMessages[i][j][k]);
                        }
                        System.out.println();
                    }
                }
            }
        }
    }
    public static void main(String[] args)
    {
        boolean del = false;
        int option, login;
        int users = 0, maxUsers = 50, maxCommunities = 50;
        int[][] friends = new int[maxUsers][maxUsers];
        int[][] requests = new int[maxUsers][maxUsers];
        int[] deletedAccounts = new int[maxUsers];
        String[][] user = new String[maxUsers][3];
        String[][] profiles = new String[maxUsers][50];
        String[][][] messages = new String[maxUsers][maxUsers][50];
        String[] communities = new String[maxCommunities];
        String[] communitiesDescription = new String[maxCommunities];
        String[][] communityUsers = new String[maxCommunities][maxUsers];
        String[][] communityRequests = new String[maxCommunities][maxUsers];
        String[][][] communityMessages = new String[maxCommunities][maxUsers][50];
        memset(friends, requests, deletedAccounts, maxUsers);

        do
        {
            option = homeMenu();
            errorThree(option);
            switch(option)
            {
                case 1:
                    login = login(user, users, deletedAccounts);
                    if(login != -1)
                    {
                        do
                        {
                            option = loginMenu(user[login][0]);
                            errorFive(option);
                            switch(option)
                            {
                                case 1:
                                    do
                                    {
                                        option = profileMenu();
                                        errorSeven(option);
                                        switch(option)
                                        {
                                            case 1:
                                                getProfile(user, profiles, login);
                                                break;
                                            case 2:
                                                do
                                                {
                                                    option = editMainProfileMenu();
                                                    errorFour(option);
                                                    editMainProfile(user, login, option);
                                                }while(option != 4);
                                                break;
                                            case 3:
                                                if(setAttributeProfile(profiles, login)) System.out.printf("Atributo adicionado com sucesso.%n%n");
                                                break;
                                            case 4:
                                                if(editAttributeProfile(profiles, login)) System.out.printf("Atributo alterado com sucesso.%n%n");
                                                break;
                                            case 5:
                                                if(removeAttributeProfile(profiles, login)) System.out.printf("Atributo removido com sucesso.%n%n");
                                                break;
                                            case 6:
                                                deleteAccount(deletedAccounts, login);
                                                del = true;
                                                break;
                                        }
                                    if(del) option = 7;
                                    }while(option != 7);
                                    break;
                                case 2:
                                    do
                                    {
                                        option = friendsMenu();
                                        errorFive(option);
                                        switch(option)
                                        {
                                            case 1:
                                                getFriends(friends, login, user, deletedAccounts);
                                                break;
                                            case 2:
                                                getRequests(user, requests, friends, login, deletedAccounts);
                                                break;
                                            case 3:
                                                setRequests(user, requests, friends, login, deletedAccounts);
                                                break;
                                            case 4:
                                                removeFriend(user, friends, login);
                                                break;
                                        }
                                    }while(option != 5);
                                    option = 2;
                                    break;
                                case 3:
                                    do
                                    {
                                        option = messageMenu();
                                        errorThree(option);
                                        switch(option)
                                        {
                                            case 1:
                                                sendMessage(user, messages, login, deletedAccounts);
                                                break;
                                            case 2:
                                                getMessages(user, messages, login, deletedAccounts);
                                                break;
                                        }
                                    }while(option != 3);
                                    break;
                                case 4:
                                    do
                                    {
                                        option = communityMenu();
                                        errorSeven(option);
                                        switch(option)
                                        {
                                            case 1:
                                                createCommunity(communities, communitiesDescription, communityUsers, user[login][0]);
                                                break;
                                            case 2:
                                                communitiesCreatedByMe(communities, communitiesDescription, communityUsers, user[login][0]);
                                                myCommunityRequests(communities, communityRequests, communityUsers, user[login][0]);
                                                break;
                                            case 3:
                                                myCommunities(communities, communitiesDescription, communityUsers, user[login][0]);
                                                break;
                                            case 4:
                                                getCommunity(communities, communityUsers, communityRequests, user[login][0]);
                                                break;
                                            case 5:
                                                messageToCommunity(communities, communityUsers, communityMessages, user[login][0]);
                                                break;
                                            case 6:
                                                getCommunityMessages(communities, communityUsers, communityMessages, user[login][0], deletedAccounts);
                                                break;
                                        }
                                    }while(option != 7);
                                    break;
                            }
                        if(del) option = 5;
                        }while(option != 5);
                    }
                    break;
                case 2:
                    if(register(user, users, deletedAccounts)) users++;
                    break;
            }
        if(del) del = false;
        }while(option != 3);
    }
}
