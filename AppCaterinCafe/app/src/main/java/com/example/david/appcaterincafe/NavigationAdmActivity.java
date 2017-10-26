package com.example.david.appcaterincafe;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.david.fragments.ActualizarCliente;
import com.example.david.fragments.CompraAhora;
import com.example.david.fragments.RegistrarUsuario;
import com.example.david.utils.Session;


public class NavigationAdmActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    FragmentManager fm = getFragmentManager();
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_adm);
        setNavigationViewListener();
        View header = ((NavigationView) findViewById(R.id.navigation)).getHeaderView(0);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
//        session = new Session(this);
//        ((TextView) header_user.findViewById(R.id.correo_usuario)).setText(session.getCorreoUsuario());
//        ((TextView) header_user.findViewById(R.id.nombre_usuario)).setText(session.getUsuarioNombre());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.buy_now:
                fm.beginTransaction().replace(R.id.fragment_place, new CompraAhora()).commit();
                break;
            case R.id.logout:
                startActivity(new Intent(NavigationAdmActivity.this, LoginActivity.class));
                break;
            case R.id.user_register:
                fm.beginTransaction().replace(R.id.fragment_place, new RegistrarUsuario()).commit();
                break;
        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
    }

}
