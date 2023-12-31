package com.koreaIT.java.AM;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("== 프로그램 시작 ==");
    Scanner sc = new Scanner(System.in);

    int lastArticleId = 0;
    List<Article> articles = new ArrayList<>();

    while (true) {
      System.out.printf("명령어 )");
      String cmd = sc.nextLine().trim();

      if (cmd.length() == 0) {
        System.out.println("명령어를 입력하세요.");
        continue;
      }

      if (cmd.equals("system exit")) {
        break;
      }
      if (cmd.equals("article write")) {
        int id = lastArticleId + 1;
        lastArticleId = id;

        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String content = sc.nextLine();

        Article article = new Article(id, title, content);
        articles.add(article);

        System.out.printf("%d 번 글이 생성 되었습니다\n", id);
      }
      else if (cmd.startsWith("article delete")) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article deleteArticle = null;

        for (int i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);
          if (article.id == id) {
            deleteArticle = article;
            articles.remove(i-0);
            System.out.printf("%d번 게시글이 삭제되었습니다.\n",id);
            break;
          }
        }

        if (deleteArticle == null) {
          System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
          continue;
        }
      }

      else if (cmd.startsWith("article detail")) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article foundArticle = null;

        for (int i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);
          if (article.id == id) {
            foundArticle = article;
            break;
          }
        }

        if (foundArticle == null) {
          System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
          continue;
        }

        System.out.printf("번호 : %d.\n", foundArticle.id);
        System.out.printf("날짜 : 2023-12-09 12:12:12\n");
        System.out.printf("제목 : %s.\n", foundArticle.title);
        System.out.printf("내용 : %s.\n", foundArticle.content);

      } else if (cmd.equals("article list")) {
        if (articles.size() == 0) {
          System.out.println("게시글이 없습니다.");
          continue;
        } else {
          for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);
            System.out.printf("%s |  %s\n", article.id, article.title);
          }
        }
      } else {
        System.out.println("존재하지 않는 명령어입니다.");
        continue;
      }
    }


    sc.close();
    System.out.println("== 프로그램 종료 ==");
  }
  }


class Article {
  int id;
  String title;
  String content;

  Article(int id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

}

//명령어 ) article delete 1
//1번 게시물이 없는 경우
//1번 게시물은 존재하지 않습니다
//1번 게시물이 있는 경우
//1번 게시물이 삭제 되었습니다
//
//힌트
//split(), startsWith()
//"arrylist 요소 삭제", "자바 현재 날짜" 검색