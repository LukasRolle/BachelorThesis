//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace DBC
{
    using System;
    using System.Collections.Generic;
    
    public partial class Pallet
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Pallet()
        {
            this.OrderLines = new HashSet<OrderLine>();
        }
    
        public int PalletNumber { get; set; }
        public string StorageLocation { get; set; }
        public Nullable<int> ArticleNumber { get; set; }
        public Nullable<int> Quantity { get; set; }
    
        public virtual Article Article { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        internal virtual ICollection<OrderLine> OrderLines { get; set; }
    }
}
