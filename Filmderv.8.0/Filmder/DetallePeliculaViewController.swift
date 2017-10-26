//
//  DetallePeliculaViewController.swift
//  Filmder
//
//  Created by Alumno801 on 10/10/17.
//  Copyright © 2017 cibertec.com.pe. All rights reserved.
//

import UIKit

class DetallePeliculaViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        setNavigationItems()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func setNavigationItems(){
        self.navigationItem.title = "Detalles"
        let l_button = UIButton(type: UIButtonType.custom)
        l_button.setTitle("RETURN", for: UIControlState.normal)
        l_button.addTarget(self, action: #selector(DetallePeliculaViewController.pressLeftButton), for: UIControlEvents.touchUpInside)
        l_button.sizeToFit()
        l_button.titleLabel!.font = UIFont(name: "Helvetica", size: 10)
        l_button.setTitleColor(UIColor.white, for: UIControlState.normal)
        self.navigationItem.leftBarButtonItem = UIBarButtonItem(customView: l_button)
    }
    
    @objc func pressLeftButton(){
        self.navigationController?.popViewController(animated: true)
    }
    

}
