//
//  EditarPerfilViewController.swift
//  Filmder
//
//  Created by Alumno801 on 10/10/17.
//  Copyright © 2017 cibertec.com.pe. All rights reserved.
//

import UIKit

class EditarPerfilViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        setNavigationItems()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()

    }
    

    func setNavigationItems(){
        self.navigationItem.title = "Editar Perfil"
        let r_button = UIButton(type: UIButtonType.custom)
        r_button.setTitle("OK", for: UIControlState.normal)
        r_button.addTarget(self, action: #selector(EditarPerfilViewController.pressRightButton), for: UIControlEvents.touchUpInside)
        r_button.sizeToFit()
        r_button.titleLabel!.font = UIFont(name: "Helvetica", size: 13)
        r_button.setTitleColor(UIColor.white, for: UIControlState.normal)
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(customView: r_button)
        
        let l_button = UIButton(type: UIButtonType.custom)
        l_button.setTitle("", for: UIControlState.normal)
        self.navigationItem.leftBarButtonItem = UIBarButtonItem(customView: r_button)
    }
    
    @objc func pressRightButton(){
        self.navigationController?.popViewController(animated: true)
    }

}
