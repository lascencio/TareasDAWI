//
//  PerfilViewController.swift
//  Filmder
//
//  Created by Alumno801 on 10/10/17.
//  Copyright © 2017 cibertec.com.pe. All rights reserved.
//

import UIKit

class PerfilViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        setNavigationItems()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func setNavigationItems(){
        self.navigationItem.title = "Perfil"
        let r_button = UIButton(type: UIButtonType.custom)
        r_button.setTitle("Editar", for: UIControlState.normal)
        r_button.addTarget(self, action: #selector(PerfilViewController.pressRightButton), for: UIControlEvents.touchUpInside)
        r_button.sizeToFit()
        r_button.titleLabel!.font = UIFont(name: "Helvetica", size: 10)
        r_button.setTitleColor(UIColor.white, for: UIControlState.normal)
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(customView: r_button)
        
        let l_button = UIButton(type: UIButtonType.custom)
        l_button.setTitle("Cerrar Sesión", for: UIControlState.normal)
        l_button.addTarget(self, action: #selector(PerfilViewController.pressLeftButton), for: UIControlEvents.touchUpInside)
        l_button.sizeToFit()
        l_button.titleLabel!.font = UIFont(name: "Helvetica", size: 10)
        l_button.setTitleColor(UIColor.white, for: UIControlState.normal)
        self.navigationItem.leftBarButtonItem = UIBarButtonItem(customView: l_button)
    }
    
    @objc func pressLeftButton(){
        presentingViewController?.dismiss(animated: true, completion: nil)
    }
    
    
    @objc func pressRightButton(){
        let vc = UIStoryboard(name: "Main", bundle: Bundle.main).instantiateViewController(withIdentifier: "EditarPerfilViewController")as! EditarPerfilViewController
        self.navigationController?.pushViewController(vc, animated: true)
    }
    

}
