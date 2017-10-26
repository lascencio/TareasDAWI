//
//  ApuntadasViewController.swift
//  Filmder
//
//  Created by Alumno801 on 10/10/17.
//  Copyright © 2017 cibertec.com.pe. All rights reserved.
//

import UIKit

class ApuntadasViewController: UIViewController, UITableViewDataSource, UITableViewDelegate{
    
    let users1 = ["Batman", "El Rey León", "Once Machos","Lego", "IT"]
    
    
    /*let users2 = [("Transformers", "Con mis hijos"),
                  ("Estación Zombie", "Cons mis patas"),
                  ("Thor: ragnarok", "Con mi novia"),
                  ("La Posesión de 1974", "Solo"),
                  ("Blade Runner 2049", "En familia")
    ]
     let users1 = [("Batman", "Con mi novia"),
     ("El Rey León", "En familia"),
     ("Once Machos", "Con mis patas"),
     ("Lego", "Con mis hijos"),
     ("IT", "Sólo")
     ]
     */
    
    let users2 = ["Con mi novia","En familia", "Con mis patas","Con mis hijos","Sólo"]
    
    
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if section == 0{
            return users1.count
        }else{
            return users2.count
        }
        
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        if section == 0{
            return "Hace una semana"
        }else{
            return "Hace dos semana"
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let vc = UIStoryboard(name: "Main", bundle: Bundle.main).instantiateViewController(withIdentifier: "EliminarPeliculaViewController")as!EliminarPeliculaViewController
        self.navigationController?.pushViewController(vc, animated: true)
    }
    

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = movieTable.dequeueReusableCell(withIdentifier: "tableViewCell", for: indexPath)as! TableViewCell
        cell.comonInit(movie: users1[indexPath.item], company: users2[indexPath.item])
        /*if indexPath.section == 0 {
         var (movie,company) = users1[indexPath.row]
            cell.textLabel?.text = movie
        }else{
            var (movie,company) = users2[indexPath.row]
            cell.textLabel?.text = movie
        }*/
        
        return cell
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationItem.title = "Peliculas Apuntadas"
        
        movieTable.dataSource = self
        movieTable.delegate = self
        
        let nibName = UINib(nibName: "TableViewCell", bundle: nil)
        movieTable.register(nibName, forCellReuseIdentifier: "tableViewCell")

    }
    
    @IBOutlet weak var movieTable: UITableView!

}
