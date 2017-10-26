
import UIKit

class TableViewCell: UITableViewCell {

    override func awakeFromNib() {
        super.awakeFromNib()
 
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

    }
    
    func comonInit(movie:String, company:String){
        lblMovie.text = movie
        lblCompany.text = company
    }
    
    @IBOutlet weak var lblCompany: UILabel!
    @IBOutlet weak var lblMovie: UILabel!
}
