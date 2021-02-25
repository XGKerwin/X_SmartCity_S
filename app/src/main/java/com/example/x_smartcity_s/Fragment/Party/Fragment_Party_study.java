package com.example.x_smartcity_s.Fragment.Party;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Adapter.Party_study_adapter;
import com.example.x_smartcity_s.R;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/22  11:17
 */
public class Fragment_Party_study extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private TextView txtPython;
    private TextView txtJava;
    private TextView txtLinux;
    private TextView txtC;
    private RecyclerView recyclerview;
    private Party_study_adapter adapter;

    private int imagepython[] = {R.drawable.python_1,R.drawable.python_2,R.drawable.python_3,};
    private String S_pyhton[] = {"Python 新手入门课","Python 异步网络编程实战","Python3 实现键值数据库"};
    private String S_pythonmsg[] = {"极度舒适的新手入门课程，面向完全没有编程基础的同学。你将在一下午入门 Linux、Python 基础和Github 常用命令，为未来的编程大楼打下稳固的基础。",
                                    "上个世纪 90 年代随着万维网的兴起，网络编程也开始逐渐发展。本课程将介绍如何使用 Socket 创建 TCP 客户端、协程原理、Linux 系统中的五种 I/O 模型、select/poll/epoll 实现 I/O 复用，以及基于 Socket 创建同步阻塞、多线程、异步程序爬取网络图片，后半部分学习异步事件库 pyuv 以及协程框架 greenlet 和 gevent 实现异步爬虫。",
                                    "本课程将通过理解一个操作类似于 Redis，存储理念来自于 CouchDB 的键值数据库的源代码来学习如何做数据库的数据存储，体会使用不可变数据结构的优点。"};

    private int imagejava[] = {R.drawable.java_1,R.drawable.java_2,R.drawable.java_3,};
    private String S_java[] = {"Java 简明教程","JDK 基础入门","MyBatis 框架基础入门"};
    private String S_javamsg[] = {"本课程作为 Java 编程的入门内容，是每个 Java 初学者都必须掌握的基础知识。课程从常量与变量、运算符、流程控制、数组和方法等 Java 基础语法开始，层层递进，逐步带你认识了解如何通过 Java 实现面向对象的三大特征继承、封装，多态。同时，课程还会涉及 Java 中常用类、字符串、集合框架和异常处理的相关操作使用。",
            "本次课程讲解了 JDK 中常用的 API，这对日常的开发十分重要。课程将涉及字符串数字处理函数，集合框架，输入输出流，以及多线程等相关知识。",
            "MyBatis 是支持定制化 SQL、存储过程以及高级映射的优秀的持久层框架。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。MyBatis 可以对配置和原生 Map 使用简单的 XML 或注解，将接口和 Java 的 POJOs(Plain Old Java Objects,普通的 Java对象)映射成数据库中的记录。本次课程详细介绍了 MyBatis 框架结构知识，并通过多个基础的 Demo 实例讲解了 MyBatis 的用法。"};

    private int imagelinux[] = {R.drawable.linux_1,R.drawable.linux_2};
    private String S_linux[] = {"Linux 基础入门","Ansible 和 Celery 运维开发平台实战"};
    private String S_linuxmsg[] = {"本课程教你如何熟练地使用 Linux，本实验中通过在线动手实验的方式学习 Linux 常用命令，用户与权限管理，目录结构与文件操作，环境变量，计划任务，管道与数据流重定向等基本知识点。",
            "这篇课程为大家提供一种管理服务器在 1000 台以内的自动化运维方案，主要实现自动化运维方案里的集中化管理的核心部分；可以为运维工作的同学提供一种解决日常工作中批量处理服务器维护性工作的方案，为从事自动化运维开发的同学提供一种自动化运维的实现思路。"};

    private int imagec[] = {R.drawable.c_1,R.drawable.c_2};
    private String S_c[] = {"C 语言实现多线程排序","C 语言实现常见数据结构"};
    private String S_cmsg[] = {"本项目在 Linux 环境下使用 C 语言多线程模型实现了排序算法，通过该项目的学习，可以理解并实践 Linux 环境的编程基础及多线程模型。",
            "本课程将通过使用 C 语言实现常见的数据结构，加深同学们对 C 语言的理解。课程将强化学员的数据结构基本功，帮助你在工作和面试脱颖而出。"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_party_study, container, false);
        initView(view);
        titleTitle.setText("党员学习");
        btn();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(linearLayoutManager);
        getshow(imagepython,S_pyhton,S_pythonmsg);


        return view;
    }

    private void btn() {
        txtPython.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPython.setTextColor(Color.WHITE);
                txtPython.setBackgroundResource(R.drawable.txt_lan_r500);
                txtJava.setTextColor(Color.GRAY);
                txtJava.setBackgroundResource(R.drawable.txt_bai_r500);
                txtC.setTextColor(Color.GRAY);
                txtC.setBackgroundResource(R.drawable.txt_bai_r500);
                txtLinux.setTextColor(Color.GRAY);
                txtLinux.setBackgroundResource(R.drawable.txt_bai_r500);

                getshow(imagepython,S_pyhton,S_pythonmsg);
            }
        });

        txtJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPython.setTextColor(Color.GRAY);
                txtPython.setBackgroundResource(R.drawable.txt_bai_r500);
                txtC.setTextColor(Color.GRAY);
                txtC.setBackgroundResource(R.drawable.txt_bai_r500);
                txtLinux.setTextColor(Color.GRAY);
                txtLinux.setBackgroundResource(R.drawable.txt_bai_r500);
                txtJava.setTextColor(Color.WHITE);
                txtJava.setBackgroundResource(R.drawable.txt_lan_r500);
                getshow(imagejava,S_java,S_javamsg);
            }
        });

        txtC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPython.setTextColor(Color.GRAY);
                txtPython.setBackgroundResource(R.drawable.txt_bai_r500);
                txtLinux.setTextColor(Color.GRAY);
                txtLinux.setBackgroundResource(R.drawable.txt_bai_r500);
                txtC.setTextColor(Color.WHITE);
                txtC.setBackgroundResource(R.drawable.txt_lan_r500);
                txtJava.setTextColor(Color.GRAY);
                txtJava.setBackgroundResource(R.drawable.txt_bai_r500);
                getshow(imagec,S_c,S_cmsg);
            }
        });

        txtLinux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtC.setTextColor(Color.GRAY);
                txtC.setBackgroundResource(R.drawable.txt_bai_r500);
                txtPython.setTextColor(Color.GRAY);
                txtPython.setBackgroundResource(R.drawable.txt_bai_r500);
                txtJava.setTextColor(Color.GRAY);
                txtJava.setBackgroundResource(R.drawable.txt_bai_r500);
                txtLinux.setTextColor(Color.WHITE);
                txtLinux.setBackgroundResource(R.drawable.txt_lan_r500);
                getshow(imagelinux,S_linux,S_linuxmsg);
            }
        });



        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Party());
            }
        });
    }

    private void getshow(int[] imagepython, String[] s_pyhton, String[] s_pythonmsg) {
        adapter = null;
        adapter = new Party_study_adapter(imagepython,s_pyhton,s_pythonmsg);
        recyclerview.setAdapter(adapter);
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        txtPython = view.findViewById(R.id.txt_python);
        txtJava = view.findViewById(R.id.txt_java);
        txtLinux = view.findViewById(R.id.txt_linux);
        txtC = view.findViewById(R.id.txt_c);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
